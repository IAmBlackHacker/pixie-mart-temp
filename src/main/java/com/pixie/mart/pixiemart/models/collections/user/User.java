package com.pixie.mart.pixiemart.models.collections.user;

import com.pixie.mart.pixiemart.interfaces.UserInformation;
import com.pixie.mart.pixiemart.models.UserKind;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import com.google.common.collect.Sets;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "User")
public class User implements UserDetails, UserInformation {
    @Id
    @Indexed(unique = true)
    private ObjectId id;

    @Version
    private Long version;

    @Indexed(unique = true)
    @NotNull
    private String username; // must be email
    private String email;

    @NotNull
    @Size(min = 6, max = 100)
    private String password;

    @NotNull
    @Size(min = 6, max = 100)
    private String authToken;

    private String firstName;
    private String middleName;
    private String lastName;
    private String profileUrl;
    private String backgroundUrl;
    private String gender;

    @Indexed(unique = true)
    private String mobileNumber;

    @CreatedDate
    @Builder.Default
    private long createdAt = Instant.now().toEpochMilli();

    @LastModifiedDate
    @Builder.Default
    private long modifiedAt = Instant.now().toEpochMilli();

    @Builder.Default
    private boolean enabled = true;

    @Builder.Default
    private boolean credentialsNonExpired = true;

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return credentialsNonExpired;
    }

    @Builder.Default
    private Set<UserKind> authorities = Sets.newHashSet(new UserKind(UserKind.NORMAL_USER));

    private List<String> address = new ArrayList<>();
    private List<OrderType> orders = new ArrayList<>();
}
