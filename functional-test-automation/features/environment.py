import features.vars.connection as vars

BEHAVE_DEBUG_ON_ERROR = True


def before_all(context):    
    vars.initialize_global_variables(context.config.userdata["INFERENCE_API_URL"])
    context.url = context.config.userdata["INFERENCE_API_URL"]
