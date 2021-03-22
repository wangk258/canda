package vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * LR树VO
 * 
 * @author Canda
 * @date 2021/3/5 3:53 PM
 */
@Data
public class LeftRightTreeVO implements Serializable {

    private static final long serialVersionUID = -5951886514211092677L;

    /**
     * 主键id
     */
    private Long id;

    private Long parentId;

    private Integer type;

    private Integer level;

    private Integer leftValue;

    private Integer rightValue;

    private String name;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private List<LeftRightTreeVO> children;

}
