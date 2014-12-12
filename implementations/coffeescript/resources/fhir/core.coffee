class Base
  constructor: (@json) ->
    @user_info = {}
  
module.exports.Base=Base

# Element is declared here instead of being generated due to the decision
# of hl7 to render extensions in JSON in a format that is not in alignment
# with the way they are declared in the Resource Models

class Element extends Base
  
  constructor: (@json) ->
    super

  id: -> 
    #there should only be one
    _val = @json._id || {}
    _val.value = @json.id
    new PrimitiveType(_value)

  getExtension: (name)->
    ext = @json[name]
    if ext
      ext.url = name
      new Extension(ext)

  # look for any property that has a ":" and that is an extension according to Grahm. 
  # if this doesn't work I know who to blame.
  extension: ->
    for x in @json
      if x.indexOf(":") > 0
        ext = @json[x]
        ext.url = x
        new Extension(ext)
