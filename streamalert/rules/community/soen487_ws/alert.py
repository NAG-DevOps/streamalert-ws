"""Alert on an incoming SOAP request."""
from stream_alert.rule_processor.rules_engine import StreamRules
rule = StreamRules.rule

@rule(logs=['alert'], outputs=['slack:soen487'], )
def alert_created(record):
    return True
