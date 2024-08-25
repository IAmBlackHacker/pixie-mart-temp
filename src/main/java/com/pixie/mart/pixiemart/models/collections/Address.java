package com.pixie.mart.pixiemart.models.collections;

import com.pixie.mart.pixiemart.constants.enums.AddressType;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;

@Data
@Builder
@Document(collection = "User_Address")
public class Address implements Serializable {
    @Id
    @Indexed(unique = true)
    private ObjectId id;

    private String firstName;
    private String lastName;
    private String addressLine1;
    private String addressLine2;

    private String city;
    private String state;
    private String pinCode;

    private String latLong;
    private String landmark;
    private String mobile;
    private String instruction;
    private AddressType addressType;

    @CreatedDate
    @Builder.Default
    private long createdAt = Instant.now().toEpochMilli();

    @LastModifiedDate
    @Builder.Default
    private long updatedAt = Instant.now().toEpochMilli();
}
