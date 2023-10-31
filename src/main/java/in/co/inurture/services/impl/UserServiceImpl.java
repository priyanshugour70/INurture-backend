package in.co.inurture.services.impl;


import in.co.inurture.dtos.PageableResponse;
import in.co.inurture.dtos.UserDto;
import in.co.inurture.entities.User;
import in.co.inurture.exceptions.ResourceNotFoundException;
import in.co.inurture.helper.Helper;
import in.co.inurture.repositories.UserRepository;
import in.co.inurture.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Value("${user.profile.image.path}")
    private String imagePath;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public UserDto createUser(UserDto userDto) {

        // Generate Unique id in string format..!
        String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);

        // Dto To Entity
        User user = dtoToEntity(userDto);
        User savedUser = userRepository.save(user);

        // Entity To Dto
        UserDto newDto = entityToDto(savedUser);
        return newDto;
    }


    @Override
    public UserDto updateUser(UserDto userDto, String userId) {

        // Get Data using Id
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found with given Id !! "));


        // Set Data
        user.setName(userDto.getName());
//        user.setEmployeeId(userDto.getEmployeeId());
//        user.setEmail(userDto.getEmail());
        user.setGender(userDto.getGender());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAbout(userDto.getAbout());
        user.setDomain(userDto.getDomain());
        user.setAddress(userDto.getAddress());
        user.setImageName(userDto.getImageName());
        user.setPassword(userDto.getPassword());

        // Save Data..
        User updatedUser = userRepository.save(user);
        UserDto updatedDto = entityToDto(updatedUser);
        return updatedDto;
    }


    @Override
    public void deleteUser(String userId) {

        // Get Data using Id
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found with given Id !! "));

        // delete user profile image..
        String fullPath = imagePath + user.getImageName();

        try{
            Path path = Paths.get(fullPath);
            Files.delete(path);
        }catch (NoSuchFileException ex){
            logger.info("User Image not found in folder..!");
            ex.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        // Delete User
        userRepository.delete(user);
    }


    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

        // Page Number default starts form 0
        Pageable pageable = PageRequest.of(pageNumber,pageSize, sort);
        Page<User> page = userRepository.findAll(pageable);

        PageableResponse<UserDto> response = Helper.getPageableResponse(page, UserDto.class);

        return response;
    }


    @Override
    public UserDto getUserById(String userId) {

        // Get Data using Id
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found with given Id !! "));

        UserDto userDto = entityToDto(user);
        return userDto;
    }

    @Override
    public UserDto getUserByEmail(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User Not Found with given Email id"));
        UserDto userDto = entityToDto(user);

        return userDto;
    }

    @Override
    public List<UserDto> searchUser(String keyword) {

        List<User> users = userRepository.findByNameContaining(keyword);
        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());

        return dtoList;
    }


    // Practice ...
    private UserDto entityToDto(User savedUser) {

//        UserDto userDto = UserDto.builder()
//                .userId(savedUser.getUserId())
//                .name(savedUser.getName())
//                .email(savedUser.getEmail())
//                .password(savedUser.getPassword())
//                .about(savedUser.getAbout())
//                .gender(savedUser.getGender())
//                .imageName(savedUser.getImageName())
//                .build();
//        return userDto;

        return mapper.map(savedUser, UserDto.class);
    }

    private User dtoToEntity(UserDto userDto) {
//        User user = User.builder()
//                .userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .about(userDto.getAbout())
//                .gender(userDto.getGender())
//                .imageName(userDto.getImageName())
//                .build();
//        return user;

        return mapper.map(userDto, User.class);
    }
}

