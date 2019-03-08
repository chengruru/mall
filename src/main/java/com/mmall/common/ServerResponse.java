package com.mmall.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * 通用的数据响应对象,当对象被返回给前端的时候，会对其进行序列化
 * @param <T> 泛型，代表我们响应对象要封装的数据
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
// 保证序列化jason的时候，如果是null的对象，key也会消失，而不会进行序列化
public class ServerResponse<T> implements Serializable {

    private int status;
    private String msg;
    // 泛型的最大好处：返回值的时候可以指定泛型的内容，也可以不指定泛型的内容
    // 如，正确的时候，可以封装一个string,错误的时候可以封装成另外的数据类型
    // 在方法声明的时候只能声明成一种类型，泛型可以很好的解决这个问题。
    // 便于业务逻辑的编写
    private T data;

    private ServerResponse(int status){
        this.status = status;
    }

    private ServerResponse(int status, T data){
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String msg){
        this.status = status;
        this.msg = msg;
    }

    private ServerResponse(int status, String msg, T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 判断当前的响应是否是正确的信息
     * @return
     */
    @JsonIgnore // 当前对外暴露的public方法不会进行序列化到json种
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    /**
     * 获取当前响应信息的状态码
     * @return
     */
    public int getStatus() {
        return status;
    }

    /**
     * 获取当前响应信息的描述信息
     * @return
     */
    public String getMsg(){
        return this.msg;
    }

    /**
     * 获取当前响应数据
     * @return
     */
    public T getData(){
        return this.data;
    }

    /**
     * 不需要传入参数信息，直接返回包含成功的状态码
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    /**
     * 包创建含响应数据的响应对象
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createBySuccessMessage(T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    /**
     * 创建包含成功信息描述的响应对象
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg);
    }

    /**
     * 包含数据和描述信息
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createBySuccessMessage(String msg, T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), ResponseCode.ERROR.getDesc());
    }

    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), errorMessage);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage){
        return new ServerResponse<T>(errorCode, errorMessage);
    }




}
