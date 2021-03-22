package service;

import entity.LeftRightTree;
import vo.LeftRightTreeVO;

import java.util.List;

/**
 * LR树服务层
 *
 * @author Canda
 * @date 2021/3/5 9:50 AM
 */
public interface LeftRightTreeService {

    /**
     * 插入节点
     *
     * @param tree
     * @return
     */
    int insert(LeftRightTree tree);

    /**
     * 更新节点
     *
     * @param tree
     * @return
     */
    int update(LeftRightTree tree);

    /**
     * 根据id获取节点
     *
     * @param id
     * @return
     */
    LeftRightTree selectById(long id);

    /**
     * 根据id删除节点
     *
     * @param id
     * @return
     */
    int deleteById(long id);

    /**
     * 更新左值
     *
     * @param value
     * @param begin
     * @param treeType
     * @return
     */
    int updateLeftValue(int value, int begin, int treeType);

    /**
     * 更新右值
     *
     * @param value
     * @param begin
     * @param treeType
     * @return
     */
    int updateRightValue(int value, int begin, int treeType);

    /**
     * 根据左右值获取孩子节点总数
     *
     * @param left
     * @param right
     * @return
     */
    int childrenCount(int left, int right);

    /**
     * 查询子节点树
     *
     * @param id
     * @return
     */
    List<LeftRightTreeVO> selectChildrenNodeTree(Long id);

}
