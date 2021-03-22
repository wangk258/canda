package dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import entity.LeftRightTree;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * LR树Dao
 *
 * @author Canda
 * @date 2021/3/4 2:52 PM
 */
@Repository
public interface LeftRightTreeDao extends BaseMapper<LeftRightTree> {

    /**
     * 更新左值
     *
     * @param value
     * @param begin
     * @param type
     * @return
     */
    int updateLeftValue(@Param("value") int value, @Param("begin") int begin, @Param("type") int type);

    /**
     * 更新右值
     *
     * @param value
     * @param begin
     * @param type
     * @return
     */
    int updateRightValue(@Param("value") int value, @Param("begin") int begin, @Param("type") int type);

    /**
     * 查询孩子节点总数
     *
     * @param leftValue
     * @param rightValue
     * @return
     */
    int childrenCount(@Param("left_value") int leftValue, @Param("right_value") int rightValue);

}
