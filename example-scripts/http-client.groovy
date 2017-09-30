/**
 * HTTP Client instatiation
 */
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.unidev.platform.components.http.HTTPClient
import com.unidev.platform.components.http.HTTPClientUtils
import com.unidev.platform.components.http.XTrustProvider


@GrabResolver(name = 'decaf', root = 'http://ci.decafdev.local/nexus/content/groups/public')
@Grab('com.unidev.platform:unidev-spring-config:2.0.0')
@Grab('com.unidev.platform:unidev-utils:2.0.0')
@GrabExclude('xml-apis:xml-apis')

        XTrustProvider.install()

HTTPClient httpClient = new HTTPClient();
httpClient.init("Potato")


return httpClient.get("http://potato.com")
