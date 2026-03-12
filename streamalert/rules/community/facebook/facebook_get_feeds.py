"""Alert on an incoming SOAP request for GitHub."""
from stream_alert.rule_processor.rules_engine import StreamRules
rule = StreamRules.rule

@rule(logs=['facebook_feeds'], outputs=['slack:soen487'])
def facebook_get_feeds(record):
    return True
    
@rule(logs=['facebook_single_feed'], outputs=['slack:soen487'])
def facebook_get_latest_feed(record):
    return True