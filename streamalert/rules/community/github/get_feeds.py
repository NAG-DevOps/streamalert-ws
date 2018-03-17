"""Alert on an incoming SOAP request for GitHub."""
from stream_alert.rule_processor.rules_engine import StreamRules
rule = StreamRules.rule

@rule(logs=['feeds'], outputs=['slack:soen487'])
def send_github_feeds_to_slack(record):
    return True