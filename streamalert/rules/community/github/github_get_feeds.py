"""Alert on an incoming SOAP request for GitHub."""
from stream_alert.rule_processor.rules_engine import StreamRules
rule = StreamRules.rule

@rule(logs=['github_feeds'], outputs=['slack:soen487'])
def github_get_feeds(record):
    return True