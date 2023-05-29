package spotting.voicepack.ayachinene;

import committee.nova.spotting.common.sound.init.Sound;
import committee.nova.spotting.common.voice.VoiceManager;
import committee.nova.spotting.common.voice.api.IVoiceType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

@Mod(AyachiNene.MODID)
public class AyachiNene {
    public static final String MODID = "svp_ayachinene";
    private static final DeferredRegister<SoundEvent> soundReg = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, AyachiNene.MODID);
    private static final Map<String, RegistryObject<SoundEvent>> sounds = new HashMap<>();

    public AyachiNene() {
        final Sound[] v = Sound.values();
        for (final Sound s : v)
            sounds.put(s.getSoundBaseName(), soundReg.register(s.getSoundBaseName(),
                    () -> new SoundEvent(new ResourceLocation(MODID, s.getSoundBaseName()))));
        soundReg.register(FMLJavaModLoadingContext.get().getModEventBus());
        VoiceManager.addVoiceType(AyachiNeneVoiceType.getInstance());
    }

    public static class AyachiNeneVoiceType implements IVoiceType {
        private static AyachiNeneVoiceType instance;

        private AyachiNeneVoiceType() {
        }

        @Override
        public ResourceLocation getVoiceId() {
            return new ResourceLocation("example", "ayachinene");
        }

        @Override
        public SoundEvent getSoundEvent(String s) {
            return sounds.get(s).get();
        }

        @Override
        public String getOptionName() {
            return "screen.spotting.cfg.option." + "ayachinene";
        }

        @Override
        public String get$Spotted$MessageKey() {
            return "msg.spotting.spotted.ayachinene";
        }

        @Override
        public String get$There$MessageKey() {
            return "msg.spotting.there.ayachinene";
        }

        public static AyachiNeneVoiceType getInstance() {
            if (instance == null) instance = new AyachiNeneVoiceType();
            return instance;
        }
    }
}
