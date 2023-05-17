package desta.si6b.kampuskitaa.Activity.API;

import desta.si6b.kampuskitaa.Activity.Model.ModelRespon;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ModelRespon> ardRetrieve();

    @FormUrlEncoded
    @POST("create.php")
    Call<ModelRespon> ardCreate(
            @Field("nama") String nama,
            @Field("kota") String kota,
            @Field("alamat") String alamat
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ModelRespon> ardUpdate(
            @Field("id") String id,
            @Field("nama") String nama,
            @Field("kota") String kota,
            @Field("alamat") String alamat
            );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ModelRespon> ardDelete(
            @Field("id") String id
            );
}
