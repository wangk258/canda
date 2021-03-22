package service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import dao.LeftRightTreeDao;
import entity.LeftRightTree;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import service.LeftRightTreeService;
import vo.LeftRightTreeVO;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Canda
 * @date 2021/3/5 9:52 AM
 */
@Service
@Slf4j
public class LeftRightTreeServiceImpl implements LeftRightTreeService {

    private static final BeanCopier beanCopier = BeanCopier.create(LeftRightTree.class, LeftRightTreeVO.class, false);

    @Autowired
    private LeftRightTreeDao leftRightTreeDao;

    @Override
    public int insert(LeftRightTree tree) {
        Date now = new Date();
        tree.setCreateTime(now);
        tree.setUpdateTime(now);
        if (tree.getParentId() == null) {
            tree.setLeftValue(1);
            tree.setRightValue(2);
            tree.setLevel(1);
            return leftRightTreeDao.insert(tree);
        }
        return insertToParent(tree);
    }

    @Override
    public int update(LeftRightTree tree) {
        tree.setUpdateTime(new Date());
        return leftRightTreeDao.updateById(tree);
    }

    @Override
    public LeftRightTree selectById(long id) {
        return leftRightTreeDao.selectById(id);
    }

    @Override
    public int deleteById(long id) {
        LeftRightTree tree = selectById(id);
        if (Objects.isNull(tree)) {
            return 0;
        }
        int childrenCount = childrenCount(tree.getLeftValue(), tree.getRightValue()) + 1;
        updateLeftValue(childrenCount * 2, tree.getLeftValue(), tree.getType());
        return leftRightTreeDao.deleteById(id);
    }

    @Override
    public int updateLeftValue(int value, int begin, int treeType) {
        return leftRightTreeDao.updateLeftValue(value, begin, treeType);
    }

    @Override
    public int updateRightValue(int value, int begin, int treeType) {
        return leftRightTreeDao.updateRightValue(value, begin, treeType);
    }

    @Override
    public int childrenCount(int left, int right) {
        return leftRightTreeDao.childrenCount(left, right);
    }

    @Override
    public List<LeftRightTreeVO> selectChildrenNodeTree(Long id) {
        if (Objects.isNull(id)) {
            return Collections.emptyList();
        }
        LeftRightTree leftRightTree = selectById(id);
        if (Objects.isNull(leftRightTree)) {
            return Collections.emptyList();
        }
        EntityWrapper<LeftRightTree> entityWrapper = new EntityWrapper<>();
        // 包含父节点
        entityWrapper.ge("left_value", leftRightTree.getLeftValue());
        entityWrapper.le("right_value", leftRightTree.getRightValue());
        List<LeftRightTree> treeList = leftRightTreeDao.selectList(entityWrapper);
        if (CollectionUtils.isEmpty(treeList)) {
            return Collections.emptyList();
        }
        List<LeftRightTreeVO> voList = treeList.stream().map(node -> convert2Vo(node)).collect(Collectors.toList());
        return buildListToTree(voList, leftRightTree.getParentId());
    }

    private List<LeftRightTreeVO> buildListToTree(List<LeftRightTreeVO> treeList, Long parentId) {
        List<LeftRightTreeVO> root = Lists.newArrayList();
        for (int i = treeList.size() - 1; i >= 0; i--) {
            LeftRightTreeVO node = treeList.get(i);
            // ||前面的条件为根节点判断
            boolean isChildNode = (parentId == null && node.getParentId() == null)
                    || (node.getParentId() != null && node.getParentId().equals(parentId));
            if (isChildNode) {
                root.add(node);
            }
        }
        Collections.reverse(root);
        for (int i = 0; i < root.size(); i++) {
            if (!CollectionUtils.isEmpty(treeList)) {
                LeftRightTreeVO t = root.get(i);
                t.setChildren(buildListToTree(treeList, t.getId()));
            }
        }
        return root;
    }

    private LeftRightTreeVO convert2Vo(LeftRightTree node) {
        if (Objects.isNull(node)) {
            return null;
        }
        LeftRightTreeVO vo = new LeftRightTreeVO();
        beanCopier.copy(node, vo, null);
        return vo;
    }

    private int insertToParent(LeftRightTree tree) {
        LeftRightTree parent = leftRightTreeDao.selectById(tree.getParentId());
        if (Objects.isNull(parent)) {
            throw new RuntimeException("指令追踪树的parent不存在");
        }
        updateLeftAndRightValue(2, parent.getRightValue() - 1, parent.getType());
        tree.setLeftValue(parent.getRightValue());
        tree.setRightValue(tree.getLeftValue() + 1);
        tree.setLevel(parent.getLevel() + 1);
        return leftRightTreeDao.insert(tree);
    }

    private void updateLeftAndRightValue(int value, int begin, int treeType) {
        updateLeftValue(value, begin, treeType);
        updateRightValue(value, begin, treeType);
    }

}
