package controller;


/**
 * @author liaojianfeng
 * @since 2020-07-31
 */
public class BaseController {

    protected ResponseResult success(Object data) {
        return new ResponseResult(data);
    }

}
