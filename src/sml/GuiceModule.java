package sml;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.*;
import sml.Translator;

/**
 * serves as the mandatory configuration class for Google Guice Assisted Inject.
 *
 *
 * @author Fabian Zischler
 */


public class GuiceModule extends AbstractModule{


        @Override
        protected void configure() {

            bind(ReflectionInstructionFactory.class).in(Singleton.class);

            install(new FactoryModuleBuilder()
                    .build(guiceInterface.class));

        }

    }
