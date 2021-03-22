package controller;

import entity.LeftRightTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.LeftRightTreeService;

/**
 * LR树控制层
 *
 * @author Canda
 * @date 2021/3/5 2:44 PM
 */
@RestController
@RequestMapping("/left-right-tree")
public class LeftRightTreeController extends BaseController {

    @Autowired
    private LeftRightTreeService leftRightTreeService;

    /**
     * 获取所以子节点（含父节点）
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/select-children-node-tree", method = RequestMethod.GET)
    public ResponseResult selectChildrenNodeTree(@RequestParam Long id) {
        return success(leftRightTreeService.selectChildrenNodeTree(id));
    }

    /**
     * 新增节点
     *
     * @param tree
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult add(@RequestBody LeftRightTree tree) {
        int id = leftRightTreeService.insert(tree);
        return success(id);
    }

}
