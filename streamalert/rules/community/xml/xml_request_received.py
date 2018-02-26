"""Alert on an incoming SOAP request."""
from stream_alert.rule_processor.rules_engine import StreamRules
rule = StreamRules.rule

@rule(logs=['test-data'],
      outputs=['slack:soen487'])
def send_xml_to_slack(record):
    return record
