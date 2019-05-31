package com.zs.sell.controller;

import com.zs.sell.dataobject.ProductCategory;
import com.zs.sell.dataobject.ProductInfo;
import com.zs.sell.service.CategoryService;
import com.zs.sell.service.ProductService;
import com.zs.sell.utils.ResultUtil;
import com.zs.sell.vo.ProductInfoVo;
import com.zs.sell.vo.ProductVo;
import com.zs.sell.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 *
 * @author fei
 * 日期: 2018-10-18
 * 时间: 0:08
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/list")
    public ResultVo list() {

        /**查询所有上架商品*/
        List<ProductInfo> productInfoList = productService.findUpAll();
        /**将上架商品的类目ID放在容器中*/
        List<Integer> categoryTypeList = new ArrayList<>();
        for (ProductInfo productInfo : productInfoList) {
            categoryTypeList.add(productInfo.getCategoryType());
        }

        /**遍历方法（java8,lambda）*/
        //List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        /**查询上架商品类目*/
        List<ProductCategory> findByCategoryTypeIn = categoryService.findByCategoryTypeIn(categoryTypeList);
        /**VO的拼接*/
        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory productCategory : findByCategoryTypeIn) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                //如果是同一类的就就查出详情
                if (productInfo.getCategoryType().equals(productCategory.getCategoryId())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    //对象的复制
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }

        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        resultVo.setData(productVoList);
        return ResultUtil.Success(productVoList);
    }
}
