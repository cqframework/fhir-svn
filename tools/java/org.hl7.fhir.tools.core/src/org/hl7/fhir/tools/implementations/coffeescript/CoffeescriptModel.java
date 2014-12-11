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
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.tools.implementations.GenBlock;
import org.hl7.fhir.utilities.Utilities;

public class CoffeescriptModel {
  private List<String> items;
  private File javaScriptFile;
  private Definitions definitions;
  private boolean includeCore;

 
  //
  public CoffeescriptModel(List<String> items, boolean includeCore, Definitions definitions, File javaScriptFile) {
    this.items = items;
    this.definitions = definitions;
    this.javaScriptFile = javaScriptFile;
    this.includeCore = includeCore;
  }

  public void generate() throws Exception {

    GenBlock fileBlock = new CSBlock();
    fileBlock.ln();
    fileBlock.ln("# Copyright (c) 2014 The MITRE Corporation");
    fileBlock.ln("# All rights reserved.");
    fileBlock.ln("# ");
    fileBlock.ln("# Redistribution and use in source and binary forms, with or without modification, ");
    fileBlock.ln("# are permitted provided that the following conditions are met:");
    fileBlock.ln("# ");
    fileBlock.ln("#     * Redistributions of source code must retain the above copyright notice, this ");
    fileBlock.ln("#       list of conditions and the following disclaimer.");
    fileBlock.ln("#     * Redistributions in binary form must reproduce the above copyright notice, ");
    fileBlock.ln("#       this list of conditions and the following disclaimer in the documentation ");
    fileBlock.ln("#       and/or other materials provided with the distribution.");
    fileBlock.ln("#     * Neither the name of HL7 nor the names of its contributors may be used to ");
    fileBlock.ln("#       endorse or promote products derived from this software without specific ");
    fileBlock.ln("#       prior written permission.");
    fileBlock.ln("# ");
    fileBlock.ln("# THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS IS\" AND ");
    fileBlock.ln("# ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED ");
    fileBlock.ln("# WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. ");
    fileBlock.ln("# IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, ");
    fileBlock.ln("# INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT ");
    fileBlock.ln("# NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR ");
    fileBlock.ln("# PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, ");
    fileBlock.ln("# WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ");
    fileBlock.ln("# ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE ");
    fileBlock.ln("# POSSIBILITY OF SUCH DAMAGE.");

    if(includeCore){
      includeCoreRequires(fileBlock);
    }
    
    for (String name : items) {

      TypeDefn root = getRootDefinition(name);
      boolean isResource = isResourceDefinition(name);
      // first loop through and generate all of the embedded types from this
      // schema
      String embExt = isResource ? "BackboneElement" : "Element";
      for (Iterator<ElementDefn> iterator = root.getElements().iterator(); iterator.hasNext();) {
        ElementDefn elementDefinition = iterator.next();
        generateEmbeddedType(fileBlock, elementDefinition,embExt);
      }
      String exts = classExtension(name);
      fileBlock.ln("###*");
      fileBlock.ln(root.getDefinition());
      fileBlock.ln("@class "+name );
      fileBlock.ln("@exports "+name +" as "+name);
      fileBlock.ln("###");
      fileBlock.ln("class " + name + " extends " + exts);
      fileBlock.bs();
      fileBlock.ln("constructor: (@json) ->");
      fileBlock.bs();
      fileBlock.ln("super(@json)");
      fileBlock.es();

      // next loop through and generate the field elements
      for (Iterator<ElementDefn> iterator = root.getElements().iterator(); iterator.hasNext();) {
        ElementDefn elementDefinition = iterator.next();
        generateElement(fileBlock, elementDefinition);
      }
      fileBlock.es();
      fileBlock.ln();
      fileBlock.ln();
      fileBlock.ln();
      fileBlock.ln("module.exports."+name+" = "+name);
    }
    

    Writer modelFile = new BufferedWriter(new FileWriter(javaScriptFile,true));
    modelFile.write(fileBlock.toString());
    modelFile.flush();
    modelFile.close();
  }

  private boolean isResourceDefinition(String name){
    ResourceDefn resource = definitions.getResources().get(name);
    resource = (resource!=null) ? resource : definitions.getBaseResources().get(name);   
    return resource!=null;
  }
  
  private String classExtension(String name){
    String ext = "Element";
    ResourceDefn resource = definitions.getResources().get(name);
    resource = (resource!=null) ? resource : definitions.getBaseResources().get(name);
    if (resource != null) {
      TypeDefn root = resource.getRoot();
      ext =  !Utilities.noString(root.typeCode()) ? root.typeCode() : "Base";
    }else if (definitions.getInfrastructure().get(name)!=null){
       ext =  (name.equals("Element"))? "Base" : "Element";
    }
    return ext;
  }
  
  private TypeDefn getRootDefinition(String name) {
    TypeDefn el = null;
    ResourceDefn resource = definitions.getResources().get(name);
    resource = (resource!=null) ? resource : definitions.getBaseResources().get(name);
    if (resource != null) {
      el = resource.getRoot();

    } else {
      el = definitions.getInfrastructure().get(name);
      el = (el == null) ? definitions.getTypes().get(name) : el;
      el = (el == null) ? definitions.getStructures().get(name) : el;
    }
    return el;
  }

  private void generateEmbeddedType(GenBlock block, ElementDefn elementDefinition, String ext) {
  
    List<TypeRef> types = elementDefinition.getTypes();
    if (types.size() == 0) {

      for (Iterator<ElementDefn> iterator1 = elementDefinition.getElements().iterator(); iterator1.hasNext();) {
        ElementDefn nestedElement = iterator1.next();
        generateEmbeddedType(block, nestedElement, ext);
      }

      String typeName = generateTypeName(elementDefinition, null);
      String cname = elementDefinition.getDeclaredTypeName();
      cname = (cname == null) ? typeName : cname;
      String className = Character.toUpperCase(cname.charAt(0)) + cname.substring(1);
     
      block.ln();
      block.ln("###* ");
      block.ln("Embedded class");
      block.ln("@class "+className);
      block.ln("@exports  "+className+" as "+className);
      block.ln("###");
      block.ln("class " + className +" extends " + ext);
      block.bs();
      block.ln("constructor: (@json) ->");
      block.bs();
      block.ln("super(@json)");
      block.es();

      for (Iterator<ElementDefn> iterator1 = elementDefinition.getElements().iterator(); iterator1.hasNext();) {
        ElementDefn nestedElement = iterator1.next();
        generateElement(block, nestedElement);
      }
      block.es();

    }
   ;
  }

  private void generateElement(GenBlock block, ElementDefn elementDefinition) {
   
    List<TypeRef> types = elementDefinition.getTypes();
    String description = elementDefinition.getDefinition();
    Integer card = elementDefinition.getMaxCardinality();
    boolean multi = (card == null || card > 1);
    
    if (types.size() > 0) {
      for (Iterator<TypeRef> iterator = types.iterator(); iterator.hasNext();) {
        TypeRef typeRef = iterator.next();
        String elementType = typeRef.getName();
        String typeName = generateTypeName(elementDefinition, typeRef);
        if (elementType.startsWith("@")) {
          elementType = typeRef.getResolvedTypeName();
        }
        if (elementType.equals("*")) {
          generateUnknownValueField(typeName,description,multi, block);
        }else if (elementType.equals("primitive")) {
          generatePrimitiveField(block, typeName);
        } else if (elementType.equals("base64Binary")) {
          generatePrimitiveValueField(typeName,"",description,multi, block);
        } else if (elementType.equals("boolean")) {
          generatePrimitiveValueField(typeName,"boolean",description,multi, block);
        } else if (elementType.equals("integer") || elementType.equals("decimal")) {
          generatePrimitiveValueField(typeName,"Number",description, multi,block);
        } else if (elementType.equals("instant") || elementType.equals("date") || elementType.equals("dateTime")) {
          generatePrimitiveValueField(typeName, "Date", description,multi,block);
        } else if (elementType.equals("string") || elementType.equals("uri") || elementType.equals("code") || elementType.equals("id")) {
          generatePrimitiveValueField(typeName, "String", description,multi,block);
        } else if (elementType.equals("Resource")) {
          if (multi) {
            generateMultiResourceValueField(typeName, "Resource",description, block);
          } else {
            generateSingleResourceValueField(typeName, "Resource", description, block);
          }

        } else if (elementType.equals("Age") || elementType.equals("Count")) {
          block.ln(typeName + ":->  new Quantity(@json['" + typeName + "'])");
        } else {
          if (multi) {
            generateMultiValueField(typeName,  elementType,description, block);
          } else {
            generateSingleValueField(typeName,  elementType, description, block);
          }
        }
      }
    } else if (types.size() == 0) {
      String typeName = generateTypeName(elementDefinition, null);
      String cname = elementDefinition.getDeclaredTypeName();
      cname = (cname == null) ? typeName : cname;
      String className = Character.toUpperCase(cname.charAt(0)) + cname.substring(1);
      if (multi) {
        generateMultiValueField(typeName,  className, description,block);
      } else {
        generateSingleValueField(typeName, className, description,block);
      }
    }
    block.ln();
  }

  public void generateReturnComment(String typeName, String description,boolean multi, GenBlock block){
    block.ln("###*");
    block.ln(description);
    if(!multi){
      block.ln("@returns {"+typeName+"}");
    }else{
      block.ln("@returns {Array} an array of {@link "+typeName+"} objects");
    }
    block.ln("###");
  }
  public void generatePrimitiveValueField(String typeName, String objectType, String description, boolean multi, GenBlock block) {
    generateReturnComment(objectType,description,multi,block);
    block.ln(typeName + ": ->");
    block.bs();
    block.ln("if @json['" + typeName + "']");
    block.bs();
    if(multi){
      block.ln("for item, i in @json['" + typeName + "']");
      block.bs();
      addPrimativeBlock(block,typeName,objectType,"item","i"); 
      block.es();
    }else {
      addPrimativeBlock(block,typeName,objectType,"@json","0");  
    }
    block.es();
    block.es();
  }
  
  
  private void generatePrimitiveField(GenBlock block,String typeName){
    block.ln(typeName + ":-> @json['"+typeName+"']");
  }
  
  private void addPrimativeBlock(GenBlock block,String typeName,String objectType,String varName,String index){
    block.ln("val = "+varName+"['"+typeName+"']");
    block.ln("_val = "+varName+"['_"+typeName+"'] || {} ");
    block.ln("_val['value'] = val");
    block.ln("_val['type'] = '"+ objectType +"'");
    block.ln(" new PrimitiveType(_val)");
  }

  
  public void generateUnknownValueField(String typeName, String description, boolean multi, GenBlock block) {
    generateReturnComment("*",description,multi,block);
    block.ln(typeName + ":->");
    block.bs();
    block.ln("if @json['" + typeName + "']");
    block.bs();
    if(multi){
      block.ln("for item in @json['" + typeName + "']");
      block.bs();
      genUnknownBlock(block,typeName,"item"); 
      block.es();
    }else {
      genUnknownBlock(block,typeName,"@json");  
    }
    block.es();
    block.es();
  }
  
  public void genUnknownBlock(GenBlock block,String typeName, String varName){
    block.ln("val = "+varName+"['"+typeName+"']");
    block.ln("if val instanceof Object");
    block.bs();
      block.ln("typ = require('./'+val.resourceType)[val.resourceType]");
      block.ln("new typ(val)");
    block.es();
    block.ln("else");
    block.bs();
      block.ln("_val = "+varName+"['_"+typeName+"'] || {} ");
      block.ln("_val['value'] = val");
      block.ln("new PrimitiveType(_val)");
    block.es();
  }
  private void generateSingleValueField(String typeName, String objectType,String description, GenBlock block) {
    generateReturnComment(objectType,description,false,block);

    block.ln(typeName + ": -> if @json['" + typeName + "'] then new " + objectType + "(@json['" + typeName + "'])");
  }

  private void generateMultiValueField(String typeName, String objectType, String description,GenBlock block) {
    generateReturnComment(objectType,description,true,block);
    block.ln(typeName + ": ->");
    block.bs();
    block.ln("if @json['" + typeName + "']");
    block.bs();
    block.ln("for item in @json['" + typeName + "']");
    block.bs();
    block.ln("new " + objectType + "(item)");
    block.es();
    block.es();
    block.es();
  }

  
  private void generateSingleResourceValueField(String typeName, String objectType,String description, GenBlock block) {
    generateReturnComment(objectType,description,false,block);
    block.ln(typeName + ": -> ");
    block.bs();
    block.ln("if @json['" + typeName + "']");
    block.bs();
    block.ln("typeName = @json['" + typeName + "'].resourceType");
    block.ln("req = require('./'+typeName.toLowerCase())[typeName]");
    block.ln("new req(@json['" + typeName + "'])");
    block.es();
    block.es();
  }
  
  private void generateMultiResourceValueField(String typeName, String objectType, String description,GenBlock block) {
    generateReturnComment("Resource",description,true,block);
    block.ln(typeName + ": ->");
    block.bs();
    block.ln("if @json['" + typeName + "']");
    block.bs();
    block.ln("for item in @json['" + typeName + "']");
    block.bs();
    block.ln("typeName = @json['" + typeName + "'].resourceType");
    block.ln("req = require('./'+typeName.toLowerCase())[typeName]");
    block.ln("new req(item)");
    block.es();
    block.es();
    block.es();
  }
  private String generateTypeName(ElementDefn elementDefinition, TypeRef type) {
    String elementName = elementDefinition.getName().replace("[x]", "");
    if (elementDefinition.getTypes().size() > 1) {
      String typeName = type.getName();
      typeName = Character.toUpperCase(typeName.charAt(0)) + typeName.substring(1);
      elementName += typeName;
    }
    return elementName;
  }
 
  private void includeCoreRequires(GenBlock block){
    block.ln("CORE = require('./core')");
    block.ln("Element = CORE.Element");
    block.ln("Resource = CORE.Resource");
    Map<String, TypeDefn> typeDefs = new HashMap<String, TypeDefn>();
    typeDefs.putAll(definitions.getTypes());
    typeDefs.putAll(definitions.getInfrastructure());
    typeDefs.putAll(definitions.getStructures());
    for(String br: definitions.getBaseResources().keySet()){
      typeDefs.put(br, definitions.getBaseResources().get(br).getRoot());
    }
    for (String name : typeDefs.keySet()){
      block.ln(name+ " = CORE."+name);
    }
  }
}
