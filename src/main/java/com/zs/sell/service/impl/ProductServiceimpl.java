package com.zs.sell.service.impl;

import com.zs.sell.dataobject.ProductInfo;
import com.zs.sell.dto.CartDto;
import com.zs.sell.enums.ProductStatusEnum;
import com.zs.sell.enums.ResultEnum;
import com.zs.sell.exception.SellException;
import com.zs.sell.repository.ProductInfoRepository;
import com.zs.sell.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author fei
 * 日期: 2018-10-17
 * 时间: 21:52
 */
@Service
@Slf4j
public class ProductServiceimpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(org.springframework.data.domain.Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDto> cartDtoList) {
        for(CartDto cartDto:cartDtoList){
            ProductInfo productInfo = productInfoRepository.findById(cartDto.getProductId()).get();
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer  result =   productInfo.getProductStock() + cartDto.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    public void decreaseStock(List<CartDto> cartDtoList) {
        /**先按订单购物车，查看数量，进行减库存操作*/
        for(CartDto cartDto:cartDtoList){
            //根据商品id查找商品
            ProductInfo productInfo = productInfoRepository.findById(cartDto.getProductId()).get();
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer  result =   productInfo.getProductStock()-cartDto.getProductQuantity();
            if(result < 0){
                throw  new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String ProductId) {

       ProductInfo productInfo = productInfoRepository.findById(ProductId).get();
       if(productInfo == null){
           log.error("【商品上架】错误，没有此商品！");
           throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
       }
       if(productInfo.getProductStatus().equals(ProductStatusEnum.UP)){
            log.error("【商品上架】错误，没有此商品！");
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
       }
        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return productInfoRepository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String ProductId) {
        ProductInfo productInfo = productInfoRepository.findById(ProductId).get();
        if(productInfo == null){
            log.error("【商品下架】错误，没有此商品！");
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatus().equals(ProductStatusEnum.DOWN)){
            log.error("【商品下架】错误，该商品已经被下架");
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productInfoRepository.save(productInfo);
    }
}
