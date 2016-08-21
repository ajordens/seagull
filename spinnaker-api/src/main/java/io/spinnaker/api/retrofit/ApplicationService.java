package io.spinnaker.api.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.Map;

public interface ApplicationService {
  @GET("/applications/{name}")
  Call<Map> getApplication(@Path("name") String applicationName);
}
