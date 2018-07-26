package org.ricone.library.config.request;


public class ConfigPathBuilder extends PathVerifier {
	private ServicePath servicePath;
	private String id;

	public ConfigPathBuilder(ServicePath servicePath) {
		this.servicePath = servicePath;
	}

	public ConfigPathBuilder id(String id) {
		this.id = id;
		return this;
	}

	public ConfigPathBase build()  {
		ConfigPathBase xPressRequest = new ConfigPathBase();
		xPressRequest.setServicePath(this.servicePath);
		xPressRequest.setId(this.id);

		/*if(isInvalidPath(xPressRequest)) {
			List<String> xPressRequestTypeValues = servicePath.getXPressRequestTypes().stream().map(RequestType::getValue).collect(Collectors.toList());
			throw new InvalidPathException(servicePath + " does not work with " + this.getClass().getCanonicalName() + ". Try a different ServicePath or use one of the following classes: " + String.join(", ", xPressRequestTypeValues));
		}

		if(isMissingId(xPressRequest)) {
			throw new MissingArgumentException(servicePath + " requires the refId method be set on " + this.getClass().getCanonicalName() + ". Set a value or try a different ServicePath.");
		}*/

		return xPressRequest;
	}

	@Override
	boolean isMissingId(ConfigPathBase request) {
		return request.isServicePathType(ServicePathType.OBJECT) && !request.hasId();
	}
}