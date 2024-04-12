//package com.ibetar.entity;
//
//package com.ibetar.recyclesync.profile;
//
//import com.ibetar.recyclesync.follower.Follower;
//import com.ibetar.recyclesync.inventory.InventoryItem;
//import com.ibetar.recyclesync.notification.Notification;
//import com.ibetar.recyclesync.order.ProfileOrder;
//import com.ibetar.recyclesync.order.dto.ProcessedOrder;
//import com.ibetar.recyclesync.profile.enums.Rating;
//import com.ibetar.recyclesync.utils.CommonEntity;
//import com.ibetar.recyclesync.wallet.Wallet;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.util.*;
//
//@Document(collection = "profiles")
//@AllArgsConstructor
//@NoArgsConstructor
//public class Profile extends CommonEntity {
//    @Id
//    private UUID id;
//
//    private String email;
//    private String aboutMe;
//    private String profileImageId = UUID.randomUUID().toString();
//    private int pointsBalance;
//    private boolean isActive;
//    private boolean kycVerified;
//    private Rating rating;
//    private LegalData legalData;
//    private Set<Wallet> profileWallets = new HashSet<>();
//    private Set<InventoryItem> inventory = new HashSet<>();
//    private List<ProfileOrder> profileOrders = new ArrayList<>();
//    private Map<String, ProcessedOrder> processedOrders = new HashMap<>();
//    private Set<Follower> followers = new HashSet<>();
//    private Set<Notification> receivedNotifications = new HashSet<>();
//    private boolean isDeleted = false;
//
//    // Constructors, getters, and setters omitted for brevity
//}
//
