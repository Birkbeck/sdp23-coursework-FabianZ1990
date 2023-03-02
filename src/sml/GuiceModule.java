package sml;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.*;

public class GuiceModule extends AbstractModule{


        @Override
        protected void configure() {

            bind(ReflectionInstructionFactory.class).in(Singleton.class);

            install(new FactoryModuleBuilder()
                    .build(guiceInterface.class));

        }

    }
