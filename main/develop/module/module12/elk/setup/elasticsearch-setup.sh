#!/bin/bash

# Exit immediately if a command exits with a non-zero status
set -e

# Wait until Elasticsearch is reachable
echo "Waiting for Elasticsearch to be ready..."
until curl -s http://$ELASTICSEARCH_HOST:$ELASTICSEARCH_PORT/_cluster/health?wait_for_status=yellow&timeout=50s >/dev/null; do
  echo "Waiting for Elasticsearch..."
  sleep 5
done

echo "Elasticsearch is up. Setting up ILM policies and index templates..."

# Create ILM Policy
curl -X PUT "http://$ELASTICSEARCH_HOST:$ELASTICSEARCH_PORT/_ilm/policy/log_policy" -H 'Content-Type: application/json' -d'
{
  "policy": {
    "phases": {
      "hot": {
        "actions": {
          "rollover": {
            "max_age": "'${LOG_RETENTION_DAYS}'d",
            "max_size": "50gb"
          }
        }
      },
      "delete": {
        "min_age": "'${LOG_RETENTION_DAYS}'d",
        "actions": {
          "delete": {}
        }
      }
    }
  }
}
'

# Create Index Template with ILM Policy
curl -X PUT "http://$ELASTICSEARCH_HOST:$ELASTICSEARCH_PORT/_template/logs_template" -H 'Content-Type: application/json' -d'
{
  "index_patterns": ["logs-*"],
  "settings": {
    "index.lifecycle.name": "log_policy",
    "index.lifecycle.rollover_alias": "logs"
  },
  "aliases": {
    "logs": {}
  }
}
'

echo "ILM policies and index templates have been set up successfully."
