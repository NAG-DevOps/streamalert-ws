"""Alert on an incoming SOAP request."""
from stream_alert.rule_processor.rules_engine import StreamRules
rule = StreamRules.rule

@rule(logs=['splints_feeds'], outputs=['slack:soen487'], )
def get_latest_splints_feed(record):
    return record['repo']['name'] == 'NAG-DevOps/splints'

@rule(logs=['wscgen_feeds'], outputs=['slack:soen487'], )
def get_latest_wscgen_feed(record):
    return record['repo']['name'] == 'GIPSY-dev/WSC-Gen'
