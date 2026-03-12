"""Alert on an incoming SOAP request."""
from stream_alert.rule_processor.rules_engine import StreamRules
rule = StreamRules.rule

@rule(logs=['github_issue'], outputs=['slack:soen487'], )
def github_get_single_issue(record):
    return True
