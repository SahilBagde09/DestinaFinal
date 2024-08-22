package com.destina.Service;

import com.destina.Dto.PackageDto;
import com.destina.model.Package;
import com.destina.model.User;
import com.destina.Repository.PackageRepository;
import com.destina.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PackageService {

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private UserRepository userRepository;

    public List<PackageDto> getAllPackages() {
        return packageRepository.findAll().stream()
                .filter(pkg -> pkg.getNumberOfSeatsAvailable() > 0)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PackageDto getPackageById(Long id) {
        Optional<Package> pkg = packageRepository.findById(id);
        return pkg.map(this::convertToDto).orElse(null);
    }

    public List<PackageDto> searchPackages(String location) {
        return packageRepository.findByLocationContaining(location).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<PackageDto> getPackagesByAgent(Long agentId) {
        return packageRepository.findByAgentId(agentId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

//    public PackageDto createPackage(String title, String description, String location, 
//                                    LocalDateTime startDate, LocalDateTime endDate, 
//                                    BigDecimal pricePerPerson, int numberOfSeatsAvailable, 
//                                    Long agentId, byte[] imageData) {
//
//        User agent = userRepository.findById(agentId).orElse(null);
//        if (agent == null) {
//            throw new IllegalArgumentException("Invalid agent ID");
//        }
//
//        Package pkg = new Package();
//        pkg.setTitle(title);
//        pkg.setDescription(description);
//        pkg.setLocation(location);
//        pkg.setStartDate(startDate);
//        pkg.setEndDate(endDate);
//        pkg.setPricePerPerson(pricePerPerson);
//        pkg.setNumberOfSeatsAvailable(numberOfSeatsAvailable);
//        pkg.setAgent(agent);
//        pkg.setImage(imageData);
//
//        Package savedPackage = packageRepository.save(pkg);
//        return convertToDto(savedPackage);
//    }
//
//    public PackageDto updatePackage(Long id, String title, String description, String location, 
//                                    LocalDateTime startDate, LocalDateTime endDate, 
//                                    BigDecimal pricePerPerson, int numberOfSeatsAvailable, 
//                                    Long agentId, byte[] imageData) {
//
//        Optional<Package> optionalPackage = packageRepository.findById(id);
//        if (!optionalPackage.isPresent()) {
//            throw new IllegalArgumentException("Package not found");
//        }
//
//        Package existingPackage = optionalPackage.get();
//        existingPackage.setTitle(title);
//        existingPackage.setDescription(description);
//        existingPackage.setLocation(location);
//        existingPackage.setStartDate(startDate);
//        existingPackage.setEndDate(endDate);
//        existingPackage.setPricePerPerson(pricePerPerson);
//        existingPackage.setNumberOfSeatsAvailable(numberOfSeatsAvailable);
//
//        if (imageData != null) {
//            existingPackage.setImage(imageData);
//        }
//
//        User agent = userRepository.findById(agentId).orElse(null);
//        if (agent != null) {
//            existingPackage.setAgent(agent);
//        }
//
//        Package updatedPackage = packageRepository.save(existingPackage);
//        return convertToDto(updatedPackage);
//    }

    public PackageDto createPackage(PackageDto packageDto) {

        User agent = userRepository.findById(packageDto.getAgentId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid agent ID"));

        Package pkg = new Package();
        pkg.setTitle(packageDto.getTitle());
        pkg.setDescription(packageDto.getDescription());
        pkg.setLocation(packageDto.getLocation());
        pkg.setStartDate(packageDto.getStartDate());
        pkg.setEndDate(packageDto.getEndDate());
        pkg.setPricePerPerson(packageDto.getPricePerPerson());
        pkg.setNumberOfSeatsAvailable(packageDto.getNumberOfSeatsAvailable());
        pkg.setAgent(agent);
        pkg.setImage(packageDto.getImage());

        Package savedPackage = packageRepository.save(pkg);
        return convertToDto(savedPackage);
    }

    public PackageDto updatePackage(Long id, PackageDto packageDto) {

        Package existingPackage = packageRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Package not found"));

        existingPackage.setTitle(packageDto.getTitle());
        existingPackage.setDescription(packageDto.getDescription());
        existingPackage.setLocation(packageDto.getLocation());
        existingPackage.setStartDate(packageDto.getStartDate());
        existingPackage.setEndDate(packageDto.getEndDate());
        existingPackage.setPricePerPerson(packageDto.getPricePerPerson());
        existingPackage.setNumberOfSeatsAvailable(packageDto.getNumberOfSeatsAvailable());

        if (packageDto.getImage() != null) {
            existingPackage.setImage(packageDto.getImage());
        }

        User agent = userRepository.findById(packageDto.getAgentId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid agent ID"));

        existingPackage.setAgent(agent);

        Package updatedPackage = packageRepository.save(existingPackage);
        return convertToDto(updatedPackage);
    }
    
    public void deletePackage(Long id) {
        packageRepository.deleteById(id);
    }

    private PackageDto convertToDto(Package pkg) {
        if (pkg == null) return null;

        String agentName = pkg.getAgent().getFirstName() + " " + pkg.getAgent().getLastName();
        return new PackageDto(
                pkg.getPackageId(),
                pkg.getTitle(),
                pkg.getImage(),
                pkg.getDescription(),
                pkg.getLocation(),
                pkg.getStartDate(),
                pkg.getEndDate(),
                pkg.getPricePerPerson(),
                pkg.getNumberOfSeatsAvailable(),
                pkg.getAgent().getId(),
                agentName
        );
    }
}
