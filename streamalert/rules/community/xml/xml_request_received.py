"""Alert on an incoming SOAP request."""
from stream_alert.rule_processor.rules_engine import StreamRules
rule = StreamRules.rule

@rule(logs=['neural-network'], outputs=['slack:soen487'])
def send_nn_to_slack(record):
    return True

@rule(logs=['marfcat-input'], outputs=['slack:soen487'])
def send_mi_to_slack(record):
    return True
