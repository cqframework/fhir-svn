package org.hl7.fhir.instance.model;

/*
  Copyright (c) 2011+, HL7, Inc.
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

// Generated on Wed, Dec 10, 2014 21:16+1100 for FHIR v0.4.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.instance.model.annotations.ResourceDef;
import org.hl7.fhir.instance.model.annotations.SearchParamDefinition;
import org.hl7.fhir.instance.model.annotations.Block;
import org.hl7.fhir.instance.model.annotations.Child;
import org.hl7.fhir.instance.model.annotations.Description;
/**
 * Represents a request for the use of a device.
 */
@ResourceDef(name="DeviceUseRequest", profile="http://hl7.org/fhir/Profile/DeviceUseRequest")
public class DeviceUseRequest extends DomainResource {

    public enum DeviceUseRequestStatus {
        /**
         * The request has been proposed.
         */
        PROPOSED, 
        /**
         * The request has been planned.
         */
        PLANNED, 
        /**
         * The request has been placed.
         */
        REQUESTED, 
        /**
         * The receiving system has received the request but not yet decided whether it will be performed.
         */
        RECEIVED, 
        /**
         * The receiving system has accepted the request but work has not yet commenced.
         */
        ACCEPTED, 
        /**
         * The work to fulfill the order is happening.
         */
        INPROGRESS, 
        /**
         * The work has been complete, the report(s) released, and no further work is planned.
         */
        COMPLETED, 
        /**
         * The request has been held by originating system/user request.
         */
        SUSPENDED, 
        /**
         * The receiving system has declined to fulfill the request.
         */
        REJECTED, 
        /**
         * The request was attempted, but due to some procedural error, it could not be completed.
         */
        ABORTED, 
        /**
         * added to help the parsers
         */
        NULL;
        public static DeviceUseRequestStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("proposed".equals(codeString))
          return PROPOSED;
        if ("planned".equals(codeString))
          return PLANNED;
        if ("requested".equals(codeString))
          return REQUESTED;
        if ("received".equals(codeString))
          return RECEIVED;
        if ("accepted".equals(codeString))
          return ACCEPTED;
        if ("in progress".equals(codeString))
          return INPROGRESS;
        if ("completed".equals(codeString))
          return COMPLETED;
        if ("suspended".equals(codeString))
          return SUSPENDED;
        if ("rejected".equals(codeString))
          return REJECTED;
        if ("aborted".equals(codeString))
          return ABORTED;
        throw new Exception("Unknown DeviceUseRequestStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case PROPOSED: return "proposed";
            case PLANNED: return "planned";
            case REQUESTED: return "requested";
            case RECEIVED: return "received";
            case ACCEPTED: return "accepted";
            case INPROGRESS: return "in progress";
            case COMPLETED: return "completed";
            case SUSPENDED: return "suspended";
            case REJECTED: return "rejected";
            case ABORTED: return "aborted";
            default: return "?";
          }
        }
        public String getSystem() {
          switch (this) {
            case PROPOSED: return "";
            case PLANNED: return "";
            case REQUESTED: return "";
            case RECEIVED: return "";
            case ACCEPTED: return "";
            case INPROGRESS: return "";
            case COMPLETED: return "";
            case SUSPENDED: return "";
            case REJECTED: return "";
            case ABORTED: return "";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case PROPOSED: return "The request has been proposed.";
            case PLANNED: return "The request has been planned.";
            case REQUESTED: return "The request has been placed.";
            case RECEIVED: return "The receiving system has received the request but not yet decided whether it will be performed.";
            case ACCEPTED: return "The receiving system has accepted the request but work has not yet commenced.";
            case INPROGRESS: return "The work to fulfill the order is happening.";
            case COMPLETED: return "The work has been complete, the report(s) released, and no further work is planned.";
            case SUSPENDED: return "The request has been held by originating system/user request.";
            case REJECTED: return "The receiving system has declined to fulfill the request.";
            case ABORTED: return "The request was attempted, but due to some procedural error, it could not be completed.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case PROPOSED: return "proposed";
            case PLANNED: return "planned";
            case REQUESTED: return "requested";
            case RECEIVED: return "received";
            case ACCEPTED: return "accepted";
            case INPROGRESS: return "in progress";
            case COMPLETED: return "completed";
            case SUSPENDED: return "suspended";
            case REJECTED: return "rejected";
            case ABORTED: return "aborted";
            default: return "?";
          }
        }
    }

  public static class DeviceUseRequestStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("proposed".equals(codeString))
          return DeviceUseRequestStatus.PROPOSED;
        if ("planned".equals(codeString))
          return DeviceUseRequestStatus.PLANNED;
        if ("requested".equals(codeString))
          return DeviceUseRequestStatus.REQUESTED;
        if ("received".equals(codeString))
          return DeviceUseRequestStatus.RECEIVED;
        if ("accepted".equals(codeString))
          return DeviceUseRequestStatus.ACCEPTED;
        if ("in progress".equals(codeString))
          return DeviceUseRequestStatus.INPROGRESS;
        if ("completed".equals(codeString))
          return DeviceUseRequestStatus.COMPLETED;
        if ("suspended".equals(codeString))
          return DeviceUseRequestStatus.SUSPENDED;
        if ("rejected".equals(codeString))
          return DeviceUseRequestStatus.REJECTED;
        if ("aborted".equals(codeString))
          return DeviceUseRequestStatus.ABORTED;
        throw new Exception("Unknown DeviceUseRequestStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == DeviceUseRequestStatus.PROPOSED)
        return "proposed";
      if (code == DeviceUseRequestStatus.PLANNED)
        return "planned";
      if (code == DeviceUseRequestStatus.REQUESTED)
        return "requested";
      if (code == DeviceUseRequestStatus.RECEIVED)
        return "received";
      if (code == DeviceUseRequestStatus.ACCEPTED)
        return "accepted";
      if (code == DeviceUseRequestStatus.INPROGRESS)
        return "in progress";
      if (code == DeviceUseRequestStatus.COMPLETED)
        return "completed";
      if (code == DeviceUseRequestStatus.SUSPENDED)
        return "suspended";
      if (code == DeviceUseRequestStatus.REJECTED)
        return "rejected";
      if (code == DeviceUseRequestStatus.ABORTED)
        return "aborted";
      return "?";
      }
    }

    public enum DeviceUseRequestPriority {
        /**
         * The request has a normal priority.
         */
        ROUTINE, 
        /**
         * The request should be done urgently.
         */
        URGENT, 
        /**
         * The request is time-critical.
         */
        STAT, 
        /**
         * The request should be acted on as soon as possible.
         */
        ASAP, 
        /**
         * added to help the parsers
         */
        NULL;
        public static DeviceUseRequestPriority fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("routine".equals(codeString))
          return ROUTINE;
        if ("urgent".equals(codeString))
          return URGENT;
        if ("stat".equals(codeString))
          return STAT;
        if ("asap".equals(codeString))
          return ASAP;
        throw new Exception("Unknown DeviceUseRequestPriority code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case ROUTINE: return "routine";
            case URGENT: return "urgent";
            case STAT: return "stat";
            case ASAP: return "asap";
            default: return "?";
          }
        }
        public String getSystem() {
          switch (this) {
            case ROUTINE: return "";
            case URGENT: return "";
            case STAT: return "";
            case ASAP: return "";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case ROUTINE: return "The request has a normal priority.";
            case URGENT: return "The request should be done urgently.";
            case STAT: return "The request is time-critical.";
            case ASAP: return "The request should be acted on as soon as possible.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case ROUTINE: return "routine";
            case URGENT: return "urgent";
            case STAT: return "stat";
            case ASAP: return "asap";
            default: return "?";
          }
        }
    }

  public static class DeviceUseRequestPriorityEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("routine".equals(codeString))
          return DeviceUseRequestPriority.ROUTINE;
        if ("urgent".equals(codeString))
          return DeviceUseRequestPriority.URGENT;
        if ("stat".equals(codeString))
          return DeviceUseRequestPriority.STAT;
        if ("asap".equals(codeString))
          return DeviceUseRequestPriority.ASAP;
        throw new Exception("Unknown DeviceUseRequestPriority code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == DeviceUseRequestPriority.ROUTINE)
        return "routine";
      if (code == DeviceUseRequestPriority.URGENT)
        return "urgent";
      if (code == DeviceUseRequestPriority.STAT)
        return "stat";
      if (code == DeviceUseRequestPriority.ASAP)
        return "asap";
      return "?";
      }
    }

    /**
     * Body site where the device is to be used.
     */
    @Child(name="bodySite", type={CodeableConcept.class}, order=-1, min=0, max=Child.MAX_UNLIMITED)
    @Description(shortDefinition="Target body site", formalDefinition="Body site where the device is to be used." )
    protected List<CodeableConcept> bodySite;

    /**
     * The status of the request.
     */
    @Child(name="status", type={CodeType.class}, order=0, min=0, max=1)
    @Description(shortDefinition="proposed | planned | requested | received | accepted | in progress | completed | suspended | rejected | aborted", formalDefinition="The status of the request." )
    protected Enumeration<DeviceUseRequestStatus> status;

    /**
     * The details of the device  to be used.
     */
    @Child(name="device", type={Device.class}, order=1, min=1, max=1)
    @Description(shortDefinition="Device requested", formalDefinition="The details of the device  to be used." )
    protected Reference device;

    /**
     * The actual object that is the target of the reference (The details of the device  to be used.)
     */
    protected Device deviceTarget;

    /**
     * An encounter that provides additional context in which this request is made.
     */
    @Child(name="encounter", type={Encounter.class}, order=2, min=0, max=1)
    @Description(shortDefinition="Encounter motivating request", formalDefinition="An encounter that provides additional context in which this request is made." )
    protected Reference encounter;

    /**
     * The actual object that is the target of the reference (An encounter that provides additional context in which this request is made.)
     */
    protected Encounter encounterTarget;

    /**
     * Identifiers assigned to this order by the orderer or by the receiver.
     */
    @Child(name="identifier", type={Identifier.class}, order=3, min=0, max=Child.MAX_UNLIMITED)
    @Description(shortDefinition="Request identifier", formalDefinition="Identifiers assigned to this order by the orderer or by the receiver." )
    protected List<Identifier> identifier;

    /**
     * Reason or justification for the use of this device.
     */
    @Child(name="indication", type={CodeableConcept.class}, order=4, min=0, max=Child.MAX_UNLIMITED)
    @Description(shortDefinition="Reason for request", formalDefinition="Reason or justification for the use of this device." )
    protected List<CodeableConcept> indication;

    /**
     * Details about this request that were not represented at all or sufficiently in one of the attributes provided in a class. These may include for example a comment, an instruction, or a note associated with the statement.
     */
    @Child(name="notes", type={StringType.class}, order=5, min=0, max=Child.MAX_UNLIMITED)
    @Description(shortDefinition="Notes or comments", formalDefinition="Details about this request that were not represented at all or sufficiently in one of the attributes provided in a class. These may include for example a comment, an instruction, or a note associated with the statement." )
    protected List<StringType> notes;

    /**
     * The proposed act must be performed if the indicated conditions occur, e.g.., shortness of breath, SpO2 less than x%.
     */
    @Child(name="prnReason", type={CodeableConcept.class}, order=6, min=0, max=Child.MAX_UNLIMITED)
    @Description(shortDefinition="PRN", formalDefinition="The proposed act must be performed if the indicated conditions occur, e.g.., shortness of breath, SpO2 less than x%." )
    protected List<CodeableConcept> prnReason;

    /**
     * The time when the request was made.
     */
    @Child(name="orderedOn", type={DateTimeType.class}, order=7, min=0, max=1)
    @Description(shortDefinition="When ordered", formalDefinition="The time when the request was made." )
    protected DateTimeType orderedOn;

    /**
     * The time at which the request was made/recorded.
     */
    @Child(name="recordedOn", type={DateTimeType.class}, order=8, min=0, max=1)
    @Description(shortDefinition="When recorded", formalDefinition="The time at which the request was made/recorded." )
    protected DateTimeType recordedOn;

    /**
     * The patient who will use the device.
     */
    @Child(name="subject", type={Patient.class}, order=9, min=1, max=1)
    @Description(shortDefinition="Focus of request", formalDefinition="The patient who will use the device." )
    protected Reference subject;

    /**
     * The actual object that is the target of the reference (The patient who will use the device.)
     */
    protected Patient subjectTarget;

    /**
     * The timing schedule for the use of the device The Schedule data type allows many different expressions, for example. "Every 8 hours"; "Three times a day"; "1/2 an hour before breakfast for 10 days from 23-Dec 2011:"; "15 Oct 2013, 17 Oct 2013 and 1 Nov 2013".
     */
    @Child(name="timing", type={Timing.class, Period.class, DateTimeType.class}, order=10, min=0, max=1)
    @Description(shortDefinition="Schedule for use", formalDefinition="The timing schedule for the use of the device The Schedule data type allows many different expressions, for example. 'Every 8 hours'; 'Three times a day'; '1/2 an hour before breakfast for 10 days from 23-Dec 2011:'; '15 Oct 2013, 17 Oct 2013 and 1 Nov 2013'." )
    protected Type timing;

    /**
     * Characterizes how quickly the  use of device must be initiated. Includes concepts such as stat, urgent, routine.
     */
    @Child(name="priority", type={CodeType.class}, order=11, min=0, max=1)
    @Description(shortDefinition="routine | urgent | stat | asap", formalDefinition="Characterizes how quickly the  use of device must be initiated. Includes concepts such as stat, urgent, routine." )
    protected Enumeration<DeviceUseRequestPriority> priority;

    private static final long serialVersionUID = -1244116449L;

    public DeviceUseRequest() {
      super();
    }

    public DeviceUseRequest(Reference device, Reference subject) {
      super();
      this.device = device;
      this.subject = subject;
    }

    /**
     * @return {@link #bodySite} (Body site where the device is to be used.)
     */
    public List<CodeableConcept> getBodySite() { 
      if (this.bodySite == null)
        this.bodySite = new ArrayList<CodeableConcept>();
      return this.bodySite;
    }

    public boolean hasBodySite() { 
      if (this.bodySite == null)
        return false;
      for (CodeableConcept item : this.bodySite)
        if (!item.isEmpty())
          return true;
      return false;
    }

    /**
     * @return {@link #bodySite} (Body site where the device is to be used.)
     */
    // syntactic sugar
    public CodeableConcept addBodySite() { //3
      CodeableConcept t = new CodeableConcept();
      if (this.bodySite == null)
        this.bodySite = new ArrayList<CodeableConcept>();
      this.bodySite.add(t);
      return t;
    }

    /**
     * @return {@link #status} (The status of the request.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<DeviceUseRequestStatus> getStatusElement() { 
      if (this.status == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create DeviceUseRequest.status");
        else if (Configuration.doAutoCreate())
          this.status = new Enumeration<DeviceUseRequestStatus>();
      return this.status;
    }

    public boolean hasStatusElement() { 
      return this.status != null && !this.status.isEmpty();
    }

    public boolean hasStatus() { 
      return this.status != null && !this.status.isEmpty();
    }

    /**
     * @param value {@link #status} (The status of the request.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public DeviceUseRequest setStatusElement(Enumeration<DeviceUseRequestStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of the request.
     */
    public DeviceUseRequestStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of the request.
     */
    public DeviceUseRequest setStatus(DeviceUseRequestStatus value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Enumeration<DeviceUseRequestStatus>();
        this.status.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #device} (The details of the device  to be used.)
     */
    public Reference getDevice() { 
      if (this.device == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create DeviceUseRequest.device");
        else if (Configuration.doAutoCreate())
          this.device = new Reference();
      return this.device;
    }

    public boolean hasDevice() { 
      return this.device != null && !this.device.isEmpty();
    }

    /**
     * @param value {@link #device} (The details of the device  to be used.)
     */
    public DeviceUseRequest setDevice(Reference value) { 
      this.device = value;
      return this;
    }

    /**
     * @return {@link #device} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The details of the device  to be used.)
     */
    public Device getDeviceTarget() { 
      if (this.deviceTarget == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create DeviceUseRequest.device");
        else if (Configuration.doAutoCreate())
          this.deviceTarget = new Device();
      return this.deviceTarget;
    }

    /**
     * @param value {@link #device} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The details of the device  to be used.)
     */
    public DeviceUseRequest setDeviceTarget(Device value) { 
      this.deviceTarget = value;
      return this;
    }

    /**
     * @return {@link #encounter} (An encounter that provides additional context in which this request is made.)
     */
    public Reference getEncounter() { 
      if (this.encounter == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create DeviceUseRequest.encounter");
        else if (Configuration.doAutoCreate())
          this.encounter = new Reference();
      return this.encounter;
    }

    public boolean hasEncounter() { 
      return this.encounter != null && !this.encounter.isEmpty();
    }

    /**
     * @param value {@link #encounter} (An encounter that provides additional context in which this request is made.)
     */
    public DeviceUseRequest setEncounter(Reference value) { 
      this.encounter = value;
      return this;
    }

    /**
     * @return {@link #encounter} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (An encounter that provides additional context in which this request is made.)
     */
    public Encounter getEncounterTarget() { 
      if (this.encounterTarget == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create DeviceUseRequest.encounter");
        else if (Configuration.doAutoCreate())
          this.encounterTarget = new Encounter();
      return this.encounterTarget;
    }

    /**
     * @param value {@link #encounter} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (An encounter that provides additional context in which this request is made.)
     */
    public DeviceUseRequest setEncounterTarget(Encounter value) { 
      this.encounterTarget = value;
      return this;
    }

    /**
     * @return {@link #identifier} (Identifiers assigned to this order by the orderer or by the receiver.)
     */
    public List<Identifier> getIdentifier() { 
      if (this.identifier == null)
        this.identifier = new ArrayList<Identifier>();
      return this.identifier;
    }

    public boolean hasIdentifier() { 
      if (this.identifier == null)
        return false;
      for (Identifier item : this.identifier)
        if (!item.isEmpty())
          return true;
      return false;
    }

    /**
     * @return {@link #identifier} (Identifiers assigned to this order by the orderer or by the receiver.)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      if (this.identifier == null)
        this.identifier = new ArrayList<Identifier>();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #indication} (Reason or justification for the use of this device.)
     */
    public List<CodeableConcept> getIndication() { 
      if (this.indication == null)
        this.indication = new ArrayList<CodeableConcept>();
      return this.indication;
    }

    public boolean hasIndication() { 
      if (this.indication == null)
        return false;
      for (CodeableConcept item : this.indication)
        if (!item.isEmpty())
          return true;
      return false;
    }

    /**
     * @return {@link #indication} (Reason or justification for the use of this device.)
     */
    // syntactic sugar
    public CodeableConcept addIndication() { //3
      CodeableConcept t = new CodeableConcept();
      if (this.indication == null)
        this.indication = new ArrayList<CodeableConcept>();
      this.indication.add(t);
      return t;
    }

    /**
     * @return {@link #notes} (Details about this request that were not represented at all or sufficiently in one of the attributes provided in a class. These may include for example a comment, an instruction, or a note associated with the statement.)
     */
    public List<StringType> getNotes() { 
      if (this.notes == null)
        this.notes = new ArrayList<StringType>();
      return this.notes;
    }

    public boolean hasNotes() { 
      if (this.notes == null)
        return false;
      for (StringType item : this.notes)
        if (!item.isEmpty())
          return true;
      return false;
    }

    /**
     * @return {@link #notes} (Details about this request that were not represented at all or sufficiently in one of the attributes provided in a class. These may include for example a comment, an instruction, or a note associated with the statement.)
     */
    // syntactic sugar
    public StringType addNotesElement() {//2 
      StringType t = new StringType();
      if (this.notes == null)
        this.notes = new ArrayList<StringType>();
      this.notes.add(t);
      return t;
    }

    /**
     * @param value {@link #notes} (Details about this request that were not represented at all or sufficiently in one of the attributes provided in a class. These may include for example a comment, an instruction, or a note associated with the statement.)
     */
    public DeviceUseRequest addNotes(String value) { //1
      StringType t = new StringType();
      t.setValue(value);
      if (this.notes == null)
        this.notes = new ArrayList<StringType>();
      this.notes.add(t);
      return this;
    }

    /**
     * @param value {@link #notes} (Details about this request that were not represented at all or sufficiently in one of the attributes provided in a class. These may include for example a comment, an instruction, or a note associated with the statement.)
     */
    public boolean hasNotes(String value) { 
      if (this.notes == null)
        return false;
      for (StringType v : this.notes)
        if (v.equals(value)) // string
          return true;
      return false;
    }

    /**
     * @return {@link #prnReason} (The proposed act must be performed if the indicated conditions occur, e.g.., shortness of breath, SpO2 less than x%.)
     */
    public List<CodeableConcept> getPrnReason() { 
      if (this.prnReason == null)
        this.prnReason = new ArrayList<CodeableConcept>();
      return this.prnReason;
    }

    public boolean hasPrnReason() { 
      if (this.prnReason == null)
        return false;
      for (CodeableConcept item : this.prnReason)
        if (!item.isEmpty())
          return true;
      return false;
    }

    /**
     * @return {@link #prnReason} (The proposed act must be performed if the indicated conditions occur, e.g.., shortness of breath, SpO2 less than x%.)
     */
    // syntactic sugar
    public CodeableConcept addPrnReason() { //3
      CodeableConcept t = new CodeableConcept();
      if (this.prnReason == null)
        this.prnReason = new ArrayList<CodeableConcept>();
      this.prnReason.add(t);
      return t;
    }

    /**
     * @return {@link #orderedOn} (The time when the request was made.). This is the underlying object with id, value and extensions. The accessor "getOrderedOn" gives direct access to the value
     */
    public DateTimeType getOrderedOnElement() { 
      if (this.orderedOn == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create DeviceUseRequest.orderedOn");
        else if (Configuration.doAutoCreate())
          this.orderedOn = new DateTimeType();
      return this.orderedOn;
    }

    public boolean hasOrderedOnElement() { 
      return this.orderedOn != null && !this.orderedOn.isEmpty();
    }

    public boolean hasOrderedOn() { 
      return this.orderedOn != null && !this.orderedOn.isEmpty();
    }

    /**
     * @param value {@link #orderedOn} (The time when the request was made.). This is the underlying object with id, value and extensions. The accessor "getOrderedOn" gives direct access to the value
     */
    public DeviceUseRequest setOrderedOnElement(DateTimeType value) { 
      this.orderedOn = value;
      return this;
    }

    /**
     * @return The time when the request was made.
     */
    public DateAndTime getOrderedOn() { 
      return this.orderedOn == null ? null : this.orderedOn.getValue();
    }

    /**
     * @param value The time when the request was made.
     */
    public DeviceUseRequest setOrderedOn(DateAndTime value) { 
      if (value == null)
        this.orderedOn = null;
      else {
        if (this.orderedOn == null)
          this.orderedOn = new DateTimeType();
        this.orderedOn.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #recordedOn} (The time at which the request was made/recorded.). This is the underlying object with id, value and extensions. The accessor "getRecordedOn" gives direct access to the value
     */
    public DateTimeType getRecordedOnElement() { 
      if (this.recordedOn == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create DeviceUseRequest.recordedOn");
        else if (Configuration.doAutoCreate())
          this.recordedOn = new DateTimeType();
      return this.recordedOn;
    }

    public boolean hasRecordedOnElement() { 
      return this.recordedOn != null && !this.recordedOn.isEmpty();
    }

    public boolean hasRecordedOn() { 
      return this.recordedOn != null && !this.recordedOn.isEmpty();
    }

    /**
     * @param value {@link #recordedOn} (The time at which the request was made/recorded.). This is the underlying object with id, value and extensions. The accessor "getRecordedOn" gives direct access to the value
     */
    public DeviceUseRequest setRecordedOnElement(DateTimeType value) { 
      this.recordedOn = value;
      return this;
    }

    /**
     * @return The time at which the request was made/recorded.
     */
    public DateAndTime getRecordedOn() { 
      return this.recordedOn == null ? null : this.recordedOn.getValue();
    }

    /**
     * @param value The time at which the request was made/recorded.
     */
    public DeviceUseRequest setRecordedOn(DateAndTime value) { 
      if (value == null)
        this.recordedOn = null;
      else {
        if (this.recordedOn == null)
          this.recordedOn = new DateTimeType();
        this.recordedOn.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #subject} (The patient who will use the device.)
     */
    public Reference getSubject() { 
      if (this.subject == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create DeviceUseRequest.subject");
        else if (Configuration.doAutoCreate())
          this.subject = new Reference();
      return this.subject;
    }

    public boolean hasSubject() { 
      return this.subject != null && !this.subject.isEmpty();
    }

    /**
     * @param value {@link #subject} (The patient who will use the device.)
     */
    public DeviceUseRequest setSubject(Reference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The patient who will use the device.)
     */
    public Patient getSubjectTarget() { 
      if (this.subjectTarget == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create DeviceUseRequest.subject");
        else if (Configuration.doAutoCreate())
          this.subjectTarget = new Patient();
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The patient who will use the device.)
     */
    public DeviceUseRequest setSubjectTarget(Patient value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #timing} (The timing schedule for the use of the device The Schedule data type allows many different expressions, for example. "Every 8 hours"; "Three times a day"; "1/2 an hour before breakfast for 10 days from 23-Dec 2011:"; "15 Oct 2013, 17 Oct 2013 and 1 Nov 2013".)
     */
    public Type getTiming() { 
      return this.timing;
    }

    /**
     * @return {@link #timing} (The timing schedule for the use of the device The Schedule data type allows many different expressions, for example. "Every 8 hours"; "Three times a day"; "1/2 an hour before breakfast for 10 days from 23-Dec 2011:"; "15 Oct 2013, 17 Oct 2013 and 1 Nov 2013".)
     */
    public Timing getTimingTiming() throws Exception { 
      if (!(this.timing instanceof Timing))
        throw new Exception("Type mismatch: the type Timing was expected, but "+this.timing.getClass().getName()+" was encountered");
      return (Timing) this.timing;
    }

    /**
     * @return {@link #timing} (The timing schedule for the use of the device The Schedule data type allows many different expressions, for example. "Every 8 hours"; "Three times a day"; "1/2 an hour before breakfast for 10 days from 23-Dec 2011:"; "15 Oct 2013, 17 Oct 2013 and 1 Nov 2013".)
     */
    public Period getTimingPeriod() throws Exception { 
      if (!(this.timing instanceof Period))
        throw new Exception("Type mismatch: the type Period was expected, but "+this.timing.getClass().getName()+" was encountered");
      return (Period) this.timing;
    }

    /**
     * @return {@link #timing} (The timing schedule for the use of the device The Schedule data type allows many different expressions, for example. "Every 8 hours"; "Three times a day"; "1/2 an hour before breakfast for 10 days from 23-Dec 2011:"; "15 Oct 2013, 17 Oct 2013 and 1 Nov 2013".)
     */
    public DateTimeType getTimingDateTimeType() throws Exception { 
      if (!(this.timing instanceof DateTimeType))
        throw new Exception("Type mismatch: the type DateTimeType was expected, but "+this.timing.getClass().getName()+" was encountered");
      return (DateTimeType) this.timing;
    }

    public boolean hasTiming() { 
      return this.timing != null && !this.timing.isEmpty();
    }

    /**
     * @param value {@link #timing} (The timing schedule for the use of the device The Schedule data type allows many different expressions, for example. "Every 8 hours"; "Three times a day"; "1/2 an hour before breakfast for 10 days from 23-Dec 2011:"; "15 Oct 2013, 17 Oct 2013 and 1 Nov 2013".)
     */
    public DeviceUseRequest setTiming(Type value) { 
      this.timing = value;
      return this;
    }

    /**
     * @return {@link #priority} (Characterizes how quickly the  use of device must be initiated. Includes concepts such as stat, urgent, routine.). This is the underlying object with id, value and extensions. The accessor "getPriority" gives direct access to the value
     */
    public Enumeration<DeviceUseRequestPriority> getPriorityElement() { 
      if (this.priority == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create DeviceUseRequest.priority");
        else if (Configuration.doAutoCreate())
          this.priority = new Enumeration<DeviceUseRequestPriority>();
      return this.priority;
    }

    public boolean hasPriorityElement() { 
      return this.priority != null && !this.priority.isEmpty();
    }

    public boolean hasPriority() { 
      return this.priority != null && !this.priority.isEmpty();
    }

    /**
     * @param value {@link #priority} (Characterizes how quickly the  use of device must be initiated. Includes concepts such as stat, urgent, routine.). This is the underlying object with id, value and extensions. The accessor "getPriority" gives direct access to the value
     */
    public DeviceUseRequest setPriorityElement(Enumeration<DeviceUseRequestPriority> value) { 
      this.priority = value;
      return this;
    }

    /**
     * @return Characterizes how quickly the  use of device must be initiated. Includes concepts such as stat, urgent, routine.
     */
    public DeviceUseRequestPriority getPriority() { 
      return this.priority == null ? null : this.priority.getValue();
    }

    /**
     * @param value Characterizes how quickly the  use of device must be initiated. Includes concepts such as stat, urgent, routine.
     */
    public DeviceUseRequest setPriority(DeviceUseRequestPriority value) { 
      if (value == null)
        this.priority = null;
      else {
        if (this.priority == null)
          this.priority = new Enumeration<DeviceUseRequestPriority>();
        this.priority.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("bodySite", "CodeableConcept", "Body site where the device is to be used.", 0, java.lang.Integer.MAX_VALUE, bodySite));
        childrenList.add(new Property("status", "code", "The status of the request.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("device", "Reference(Device)", "The details of the device  to be used.", 0, java.lang.Integer.MAX_VALUE, device));
        childrenList.add(new Property("encounter", "Reference(Encounter)", "An encounter that provides additional context in which this request is made.", 0, java.lang.Integer.MAX_VALUE, encounter));
        childrenList.add(new Property("identifier", "Identifier", "Identifiers assigned to this order by the orderer or by the receiver.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("indication", "CodeableConcept", "Reason or justification for the use of this device.", 0, java.lang.Integer.MAX_VALUE, indication));
        childrenList.add(new Property("notes", "string", "Details about this request that were not represented at all or sufficiently in one of the attributes provided in a class. These may include for example a comment, an instruction, or a note associated with the statement.", 0, java.lang.Integer.MAX_VALUE, notes));
        childrenList.add(new Property("prnReason", "CodeableConcept", "The proposed act must be performed if the indicated conditions occur, e.g.., shortness of breath, SpO2 less than x%.", 0, java.lang.Integer.MAX_VALUE, prnReason));
        childrenList.add(new Property("orderedOn", "dateTime", "The time when the request was made.", 0, java.lang.Integer.MAX_VALUE, orderedOn));
        childrenList.add(new Property("recordedOn", "dateTime", "The time at which the request was made/recorded.", 0, java.lang.Integer.MAX_VALUE, recordedOn));
        childrenList.add(new Property("subject", "Reference(Patient)", "The patient who will use the device.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("timing[x]", "Timing|Period|dateTime", "The timing schedule for the use of the device The Schedule data type allows many different expressions, for example. 'Every 8 hours'; 'Three times a day'; '1/2 an hour before breakfast for 10 days from 23-Dec 2011:'; '15 Oct 2013, 17 Oct 2013 and 1 Nov 2013'.", 0, java.lang.Integer.MAX_VALUE, timing));
        childrenList.add(new Property("priority", "code", "Characterizes how quickly the  use of device must be initiated. Includes concepts such as stat, urgent, routine.", 0, java.lang.Integer.MAX_VALUE, priority));
      }

      public DeviceUseRequest copy() {
        DeviceUseRequest dst = new DeviceUseRequest();
        copyValues(dst);
        if (bodySite != null) {
          dst.bodySite = new ArrayList<CodeableConcept>();
          for (CodeableConcept i : bodySite)
            dst.bodySite.add(i.copy());
        };
        dst.status = status == null ? null : status.copy();
        dst.device = device == null ? null : device.copy();
        dst.encounter = encounter == null ? null : encounter.copy();
        if (identifier != null) {
          dst.identifier = new ArrayList<Identifier>();
          for (Identifier i : identifier)
            dst.identifier.add(i.copy());
        };
        if (indication != null) {
          dst.indication = new ArrayList<CodeableConcept>();
          for (CodeableConcept i : indication)
            dst.indication.add(i.copy());
        };
        if (notes != null) {
          dst.notes = new ArrayList<StringType>();
          for (StringType i : notes)
            dst.notes.add(i.copy());
        };
        if (prnReason != null) {
          dst.prnReason = new ArrayList<CodeableConcept>();
          for (CodeableConcept i : prnReason)
            dst.prnReason.add(i.copy());
        };
        dst.orderedOn = orderedOn == null ? null : orderedOn.copy();
        dst.recordedOn = recordedOn == null ? null : recordedOn.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.timing = timing == null ? null : timing.copy();
        dst.priority = priority == null ? null : priority.copy();
        return dst;
      }

      protected DeviceUseRequest typedCopy() {
        return copy();
      }

      public boolean isEmpty() {
        return super.isEmpty() && (bodySite == null || bodySite.isEmpty()) && (status == null || status.isEmpty())
           && (device == null || device.isEmpty()) && (encounter == null || encounter.isEmpty()) && (identifier == null || identifier.isEmpty())
           && (indication == null || indication.isEmpty()) && (notes == null || notes.isEmpty()) && (prnReason == null || prnReason.isEmpty())
           && (orderedOn == null || orderedOn.isEmpty()) && (recordedOn == null || recordedOn.isEmpty())
           && (subject == null || subject.isEmpty()) && (timing == null || timing.isEmpty()) && (priority == null || priority.isEmpty())
          ;
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.DeviceUseRequest;
   }

  @SearchParamDefinition(name="patient", path="DeviceUseRequest.subject", description="Search by subject - a patient", type="reference" )
  public static final String SP_PATIENT = "patient";
  @SearchParamDefinition(name="subject", path="DeviceUseRequest.subject", description="Search by subject", type="reference" )
  public static final String SP_SUBJECT = "subject";

}

