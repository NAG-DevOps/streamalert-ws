from stream_alert.rule_processor.rules_engine import StreamRules
rule = StreamRules.rule

@rule(logs=['soen487-xml-kv'],               # applicable datasource(s)
      outputs=['slack:pm1-notifications'])   # where to send alerts
def xml_to_slack_2(record):                  # the rule name will be 'example'
    # code   
    print record                             # analyze the incoming record w/ your logic
    return record                            # return True if an alert should be sents