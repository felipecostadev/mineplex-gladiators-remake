package com.mineplex.core.util;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketListenerPriority;

public record PacketHolder(PacketListener listener, PacketListenerPriority priority) {

}