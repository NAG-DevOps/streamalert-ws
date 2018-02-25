"""Alert on an incoming SOAP request."""
from stream_alert.rule_processor.rules_engine import StreamRules
rule = StreamRules.rule


@rule(logs=['test-data'],               # applicable datasource(s)
      outputs=['slack:soen487'])   # where to send alerts
def send_xml_to_slack(record):                  # the rule name will be 'example'
    return record
