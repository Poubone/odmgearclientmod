package fr.poubone;

import io.netty.buffer.Unpooled;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class OdmGearClientModClient implements ClientModInitializer {


	private static KeyBinding[] keys;

	@Override
	public void onInitializeClient() {
		PayloadTypeRegistry.playC2S().register(SendOneC2SPayload.ID, SendOneC2SPayload.CODEC);

		keys = new KeyBinding[] {
				registerKey("reel", GLFW.GLFW_KEY_P),             // ID = 1
				registerKey("detach_hook", GLFW.GLFW_KEY_O),      // ID = 2
				registerKey("gas_thrust", GLFW.GLFW_KEY_L),       // ID = 3
				registerKey("dispense_gas_left", GLFW.GLFW_KEY_K),// ID = 4
				registerKey("dispense_gas_right", GLFW.GLFW_KEY_J),// ID = 5
				registerKey("reel_wires_out", GLFW.GLFW_KEY_I),   // ID = 6
				registerKey("reload_blades", GLFW.GLFW_KEY_U)     // ID = 7
		};

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			for (int i = 0; i < keys.length; i++) {
				if (keys[i].wasPressed()) {
					int id = i + 1;
					if (ClientPlayNetworking.canSend(SendOneC2SPayload.ID)) {
						ClientPlayNetworking.send(new SendOneC2SPayload(id));
					}
				}
			}
		});
	}

	private KeyBinding registerKey(String name, int defaultKey) {
		return KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.odmgearclientmod." + name,
				InputUtil.Type.KEYSYM,
				defaultKey,
				"category.odmgearclientmod"
		));
	}
}
