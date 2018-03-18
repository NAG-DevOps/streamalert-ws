"""Alert on an incoming SOAP request."""
from stream_alert.rule_processor.rules_engine import StreamRules
rule = StreamRules.rule

@rule(logs=['splints_feeds'], outputs=['slack:soen487'], )
def get_latest_splints_feed(record):
    return True
