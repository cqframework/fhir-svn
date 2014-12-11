package org.hl7.fhir.tools.implementations.coffeescript;

/*
Contributed by Mitre Corporation

Copyright (c) 2011-2014, HL7, Inc & The MITRE Corporation
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, 
are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this 
   list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
   this list of conditions and the following disclaimer in the documentation 
   and/or other materials provided with the distribution.
 * Neither the name of HL7 nor the names of its contributors may be used to 
   endorse or promote products derived from this software without specific 
   prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.

 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.tools.implementations.BaseGenerator;
import org.hl7.fhir.tools.implementations.GenBlock;
import org.hl7.fhir.tools.publisher.PlatformGenerator;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.Utilities;

public class CoffeescriptGenerator extends BaseGenerator implements PlatformGenerator {
  
  public static char SEPARATOR = File.separatorChar;

  @Override
  public String getName() {
    return "coffeescript";
  }

  @Override
  public String getTitle() {
    return "CoffeeScript";
  }


  public String getDescription(String version, String svnVersion) {
    return "Generates Coffeescript models for FHIR resources";
  }

  @Override
  public String getVersion() {
    return "0.1";
  }

  @Override
  public boolean isECoreGenerator() {
    return false;
  }

  @Override
  public void generate(Definitions definitions, String destDir, String implDir, String version, Date genDate, Logger logger, String svnRevision) throws Exception {
    
    String resourcesDir = Utilities.path(implDir, "resources");
    final String basedDir = Utilities.path(implDir, "generated");
    Utilities.createDirectory(resourcesDir);
    Utilities.createDirectory(basedDir);
    Utilities.clearDirectory(basedDir);
    GenBlock block = new CSBlock();
    
    Map<String, String> dirs = new HashMap<String, String>() {{
      put("modelDir",              Utilities.path(basedDir, "fhir"));
    }};
    
    createDirStructrue(dirs);
    Utilities.copyDirectory(resourcesDir, basedDir, null);
    
    List<String> typeDefs = new ArrayList<String>();
    typeDefs.add("Element");
    typeDefs.add("BackboneElement");
    typeDefs.add("PrimitiveType");
    
    definitions.getInfrastructure().put("PrimitiveType", generatePrimitiveTypeDef());
    addAll(typeDefs,definitions.getInfrastructure().keySet());
    addAll(typeDefs,definitions.getTypes().keySet());
    addAll(typeDefs,definitions.getStructures().keySet());
    //definitions.
    typeDefs.add("Resource");
    typeDefs.add("DomainResource");
    addAll(typeDefs,definitions.getBaseResources().keySet());
    
    //typeDefs.putAll(definitions.getInfrastructure());
   /* typeDefs.putAll(definitions.getTypes());
    typeDefs.putAll(definitions.getStructures());
    typeDefs.putAll(definitions.getBaseResources());
    */

    generateCoffeescriptModel("core",dirs.get("modelDir"),false,typeDefs,definitions);
    block.ln("module.exports = require './core'");
    Map<String, ResourceDefn> namesAndDefinitions = definitions.getResources();
    for (String name : namesAndDefinitions.keySet()) {
      List<String> l = new ArrayList<String>();
      l.add(name);
      generateCoffeescriptModel(name,dirs.get("modelDir"),true,l,definitions);
      block.ln("module.exports."+name+" = require('./"+name.toLowerCase()+"')."+name);
    }
   
    File modelFile = new File(Utilities.path(dirs.get("modelDir"),"models.coffee"));
    Writer writer = new BufferedWriter(new FileWriter(modelFile));
    writer.write(block.toString());
    writer.flush();
    writer.close();
  }
  
  private TypeDefn generatePrimitiveTypeDef(){
    TypeDefn t = new TypeDefn();
    t.setName("PrimitiveType");
    ElementDefn d = new ElementDefn();
    d.setName("value");
    d.setDeclaredTypeName("*");
    d.setMaxCardinality(1);
    d.getTypes().add(new TypeRef("primitive"));
    t.getElements().add(d);
    return t;
  }
  private void addAll(List<String> types, Set<String> toAdd){
    for(String name : toAdd){
      if(!types.contains(name)){
        types.add(name);
      }
    }
  }
  private void createDirStructrue(Map<String, String> dirs) throws IOException {
    for (String dir : dirs.values()) {
      Utilities.createDirectory(Utilities.path(dir));
      Utilities.clearDirectory(Utilities.path(dir));
    }
  }


  private void generateCoffeescriptModel(String fileName, String modelDir, boolean includeCore,  List typeDefs, Definitions definitions) throws Exception {
    File modelFile = new File(Utilities.path(modelDir, fileName.toLowerCase() + ".coffee"));
    CoffeescriptModel model = new CoffeescriptModel(typeDefs,includeCore,definitions,modelFile);
    model.generate();
  }
  
 
  @Override
  public boolean doesCompile() {
    return false;
  }

  @Override
  public boolean doesTest() {
    return false;
  }

  @Override
  public void loadAndSave(String rootDir, String sourceFile, String destFile) throws Exception {
  }

  @Override
  public void generate(org.hl7.fhir.definitions.ecore.fhir.Definitions definitions, String destDir, String implDir, String version, Date genDate, Logger logger, String svnRevision) throws Exception {
  }

  @Override
  public boolean compile(String rootDir, List<String> errors, Logger logger) throws Exception {
    return false;
  }


  public String checkFragments(String rootDir, String fragmentsXml) throws Exception {
    return null;
  }

}
