// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: users.proto

// Protobuf Java Version: 3.25.1
package com.rlgino.CardsService.infrastructure.usersproto;

public interface UserOrBuilder extends
    // @@protoc_insertion_point(interface_extends:usersproto.User)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string id = 1;</code>
   * @return The id.
   */
  java.lang.String getId();
  /**
   * <code>string id = 1;</code>
   * @return The bytes for id.
   */
  com.google.protobuf.ByteString
      getIdBytes();

  /**
   * <code>string name = 2;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 2;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>string lastName = 3;</code>
   * @return The lastName.
   */
  java.lang.String getLastName();
  /**
   * <code>string lastName = 3;</code>
   * @return The bytes for lastName.
   */
  com.google.protobuf.ByteString
      getLastNameBytes();

  /**
   * <code>string datetime = 4;</code>
   * @return The datetime.
   */
  java.lang.String getDatetime();
  /**
   * <code>string datetime = 4;</code>
   * @return The bytes for datetime.
   */
  com.google.protobuf.ByteString
      getDatetimeBytes();

  /**
   * <code>.usersproto.User.ItemStatus status = 5;</code>
   * @return The enum numeric value on the wire for status.
   */
  int getStatusValue();
  /**
   * <code>.usersproto.User.ItemStatus status = 5;</code>
   * @return The status.
   */
  com.rlgino.CardsService.infrastructure.usersproto.User.ItemStatus getStatus();
}
