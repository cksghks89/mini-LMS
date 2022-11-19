package com.example.minilms.admin.service.impl;

import com.example.minilms.admin.dto.BannerDto;
import com.example.minilms.admin.entity.Banner;
import com.example.minilms.admin.mapper.BannerMapper;
import com.example.minilms.admin.model.BannerInput;
import com.example.minilms.admin.model.BannerParam;
import com.example.minilms.admin.repository.BannerRepository;
import com.example.minilms.admin.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {
    private final BannerMapper bannerMapper;
    private final BannerRepository bannerRepository;

    @Override
    public List<BannerDto> list(BannerParam parameter) {
        long totalCount = bannerMapper.selectListCount(parameter);
        List<BannerDto> list = bannerMapper.selectList(parameter);
        if(!CollectionUtils.isEmpty(list)){
            int i = 0;
            for(BannerDto x : list){
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }
        return list;
    }

    @Override
    public BannerDto getById(long id) {
        return bannerRepository.findById(id)
                .map(BannerDto::of).orElse(null);
    }

    @Override
    public boolean set(BannerInput parameter) {
        Optional<Banner> optionalBanner = bannerRepository.findById(parameter.getId());
        if(optionalBanner.isEmpty()){
            return false;
        }

        Banner banner = optionalBanner.get();

        banner.setBannerId(parameter.getBannerId());
        banner.setLinkPath(parameter.getLinkPath());
        banner.setOpenMethod(parameter.getOpenMethod());
        banner.setSortValue(parameter.getSortValue());
        banner.setPublic(parameter.getIsPublic() != null && parameter.getIsPublic().equals("true"));
        banner.setFileName(parameter.getFileName());
        banner.setUrlFileName(parameter.getUrlFileName());
        banner.setAlterText(parameter.getAlterText());

        bannerRepository.save(banner);

        return true;
    }

    @Override
    public boolean add(BannerInput parameter) {
        Banner banner = Banner.builder()
                .bannerId(parameter.getBannerId())
                .linkPath(parameter.getLinkPath())
                .sortValue(parameter.getSortValue())
                .isPublic(parameter.getIsPublic() != null && parameter.getIsPublic().equals("true"))
                .alterText(parameter.getAlterText())
                .openMethod(parameter.getOpenMethod())
                .fileName(parameter.getFileName())
                .urlFileName(parameter.getUrlFileName())
                .regDt(LocalDateTime.now())
                .build();

        bannerRepository.save(banner);
        return true;
    }
}
