// Code generated by Wire protocol buffer compiler, do not edit.
// Source: RequestProto in request.proto
package com.github.kazukinr.flutter_message_channels.proto

import com.squareup.wire.FieldEncoding
import com.squareup.wire.Message
import com.squareup.wire.ProtoAdapter
import com.squareup.wire.ProtoReader
import com.squareup.wire.ProtoWriter
import com.squareup.wire.Syntax.PROTO_3
import com.squareup.wire.WireField
import com.squareup.wire.`internal`.sanitize
import kotlin.Any
import kotlin.AssertionError
import kotlin.Boolean
import kotlin.Deprecated
import kotlin.DeprecationLevel
import kotlin.Int
import kotlin.Long
import kotlin.Nothing
import kotlin.String
import kotlin.Unit
import kotlin.hashCode
import kotlin.jvm.JvmField
import okio.ByteString

public class RequestProto(
  @field:WireField(
    tag = 1,
    adapter = "com.squareup.wire.ProtoAdapter#STRING",
    label = WireField.Label.OMIT_IDENTITY,
    declaredName = "param"
  )
  public val param_: String = "",
  unknownFields: ByteString = ByteString.EMPTY
) : Message<RequestProto, Nothing>(ADAPTER, unknownFields) {
  @Deprecated(
    message = "Shouldn't be used in Kotlin",
    level = DeprecationLevel.HIDDEN
  )
  public override fun newBuilder(): Nothing = throw AssertionError()

  public override fun equals(other: Any?): Boolean {
    if (other === this) return true
    if (other !is RequestProto) return false
    if (unknownFields != other.unknownFields) return false
    if (param_ != other.param_) return false
    return true
  }

  public override fun hashCode(): Int {
    var result = super.hashCode
    if (result == 0) {
      result = unknownFields.hashCode()
      result = result * 37 + param_.hashCode()
      super.hashCode = result
    }
    return result
  }

  public override fun toString(): String {
    val result = mutableListOf<String>()
    result += """param_=${sanitize(param_)}"""
    return result.joinToString(prefix = "RequestProto{", separator = ", ", postfix = "}")
  }

  public fun copy(param_: String = this.param_, unknownFields: ByteString = this.unknownFields):
      RequestProto = RequestProto(param_, unknownFields)

  public companion object {
    @JvmField
    public val ADAPTER: ProtoAdapter<RequestProto> = object : ProtoAdapter<RequestProto>(
      FieldEncoding.LENGTH_DELIMITED, 
      RequestProto::class, 
      "type.googleapis.com/RequestProto", 
      PROTO_3, 
      null
    ) {
      public override fun encodedSize(value: RequestProto): Int {
        var size = value.unknownFields.size
        if (value.param_ != "") size += ProtoAdapter.STRING.encodedSizeWithTag(1, value.param_)
        return size
      }

      public override fun encode(writer: ProtoWriter, value: RequestProto): Unit {
        if (value.param_ != "") ProtoAdapter.STRING.encodeWithTag(writer, 1, value.param_)
        writer.writeBytes(value.unknownFields)
      }

      public override fun decode(reader: ProtoReader): RequestProto {
        var param_: String = ""
        val unknownFields = reader.forEachTag { tag ->
          when (tag) {
            1 -> param_ = ProtoAdapter.STRING.decode(reader)
            else -> reader.readUnknownField(tag)
          }
        }
        return RequestProto(
          param_ = param_,
          unknownFields = unknownFields
        )
      }

      public override fun redact(value: RequestProto): RequestProto = value.copy(
        unknownFields = ByteString.EMPTY
      )
    }

    private const val serialVersionUID: Long = 0L
  }
}
