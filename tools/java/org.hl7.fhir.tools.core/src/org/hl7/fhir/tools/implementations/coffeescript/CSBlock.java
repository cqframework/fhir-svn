package org.hl7.fhir.tools.implementations.coffeescript;

import org.hl7.fhir.tools.implementations.GenBlock;

public class CSBlock extends GenBlock {



  public void bs()
  {
    marginHistory.push(margin);
    margin += 2;    
  }

}
