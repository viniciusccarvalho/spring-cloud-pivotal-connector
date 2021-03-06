package org.springframework.cloud.pivotal.cloudfoundry;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.cloud.pivotal.service.common.GemfireServiceInfo;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GemfireInfoCreatorTest {

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testInfoCreator() throws Exception {
		GemfireInfoCreator creator = new GemfireInfoCreator();
		Map services = mapper.readValue(GemfireInfoCreatorTest.class.getClassLoader().getSystemResourceAsStream("test-gemfire-service.json"), Map.class);
		Map<String, Object> serviceData = (Map<String, Object>) ((List) services.get("p-gemfire")).get(0);

		GemfireServiceInfo info = creator.createServiceInfo(serviceData);
		Assert.assertNotNull(info);
	}

	@Test
	public void testAcceptService() throws Exception {
		GemfireInfoCreator creator = new GemfireInfoCreator();
		Map services = mapper.readValue(GemfireInfoCreatorTest.class.getClassLoader().getSystemResourceAsStream("test-gemfire-service.json"), Map.class);
		Map<String, Object> serviceData = (Map<String, Object>) ((List) services.get("p-gemfire")).get(0);
		boolean accepts = creator.accept(serviceData);
		Assert.assertEquals(true, accepts);
	}
	
	@Test
	public void testInfoCreatorCups() throws Exception {
		GemfireInfoCreator creator = new GemfireInfoCreator();
		Map services = mapper.readValue(GemfireInfoCreatorTest.class.getClassLoader().getSystemResourceAsStream("test-gemfire-userprovided.json"), Map.class);
		Map<String, Object> serviceData = (Map<String, Object>) ((List) services.get("user-provided")).get(0);

		GemfireServiceInfo info = creator.createServiceInfo(serviceData);
		Assert.assertNotNull(info);
	}
	@Test
	public void testAcceptCups() throws Exception {
		GemfireInfoCreator creator = new GemfireInfoCreator();
		Map services = mapper.readValue(GemfireInfoCreatorTest.class.getClassLoader().getSystemResourceAsStream("test-gemfire-userprovided.json"), Map.class);
		Map<String, Object> serviceData = (Map<String, Object>) ((List) services.get("user-provided")).get(0);
		boolean accepts = creator.accept(serviceData);
		Assert.assertEquals(true, accepts);
	}
	
}
