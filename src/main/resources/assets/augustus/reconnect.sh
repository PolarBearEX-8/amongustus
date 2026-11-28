
#!/bin/bash

# XML body for the SOAP request
body='<?xml version="1.0" encoding="utf-8"?>
<s:Envelope xmlns:s="http://schemas.xmlsoap.org/soap/envelope/" s:encodingStyle="http://schemas.xmlsoap.org/soap/encoding/">
  <s:Body>
    <u:ForceTermination xmlns:u="urn:schemas-upnp-org:service:WANIPConnection:1" />
  </s:Body>
</s:Envelope>'

# URLs to try for the SOAP request
urls=("igd" "")

# Loop through the URLs
for url in "${urls[@]}"; do
  curl -X POST "http://fritz.box:49000/${url}upnp/control/WANIPConn1" \
    -H "Content-Type: text/xml; charset=utf-8" \
    -H "Connection: close" \
    -H "Content-Length: ${#body}" \
    -H "HOST: fritz.box:49000" \
    -H 'SOAPACTION: "urn:schemas-upnp-org:service:WANIPConnection:1#ForceTermination"' \
    --data "$body"
done
