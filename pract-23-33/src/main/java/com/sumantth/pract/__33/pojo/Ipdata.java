package com.sumantth.pract.__33.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ipdata {
//{ip=108.88.174.110, hostname=108-88-174-110.lightspeed.livnmi.sbcglobal.net,
// city=Walled Lake, region=Michigan, country=US, loc=42.5582,-83.4773, org=AS7018 AT&T Services, Inc.,
// postal=48390, timezone=America/Detroit, readme=https://ipinfo.io/missingauth}
    private String ip;
    private String hostname;
    private String country;
    private String loc;
    private String  org;
    private String  postal;
    private String timezone;
    private String readme;
}
