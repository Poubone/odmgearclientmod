package fr.poubone;

import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.network.PacketByteBuf;
import io.netty.buffer.Unpooled;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.util.Identifier;

public record SendOneC2SPayload(int value) implements CustomPayload {
    public static final Identifier ID_RAW = Identifier.of("odmgearclientmod:channel");
    public static final Id<SendOneC2SPayload> ID = new Id<>(ID_RAW);
    public static final PacketCodec<RegistryByteBuf, SendOneC2SPayload> CODEC =
            PacketCodec.tuple(PacketCodecs.INTEGER, SendOneC2SPayload::value, SendOneC2SPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}

