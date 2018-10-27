package pers.wl.album.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import pers.wl.album.model.GoodsInfoModel;
public interface GoodsInfoRepository extends JpaRepository<GoodsInfoModel,Integer>,JpaSpecificationExecutor<GoodsInfoModel> {
}