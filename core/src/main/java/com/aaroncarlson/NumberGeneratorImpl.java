package com.aaroncarlson;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aaroncarlson.annotation.MaximumNumber;
import com.aaroncarlson.annotation.MinimumNumber;

import lombok.AccessLevel;
import lombok.Getter;

/*
 * @Component annotation tells the Spring container to automatically initialize the class (as a singleton) when performing
 * @ComponentScan and autowire beans/dependencies for the class.
 * NOTE: annotation is added to the implementation of the interface and not the interface itself since interfaces
 * should NOT be dependent on Spring (aka: interfaces should be decoupled from implementation)
 * NOTE: OLD WAY - When @Autowired creates an instance of the NumberGenarator bean the default name is the name of the
 * interface class (in this case NumberGenerator). If there are multiple implementations of the interface a name must
 * be given to the implementing/concrete class (ex: @Component("generator"))
 */
@Getter
@Component
public class NumberGeneratorImpl implements NumberGenerator {

    // == fields ==
    private final int maximumNumber;
    private final int minimumNumber;
    @Getter(AccessLevel.NONE)
    private final Random random = new Random();

    // == constructors ==
    @Autowired
    public NumberGeneratorImpl(@MinimumNumber int minimumNumber, @MaximumNumber int maximumNumber) {
        this.minimumNumber = minimumNumber;
        this.maximumNumber = maximumNumber;
    }


    // == public methods ==
    @Override
    public int next() {
        // logic: min = 5 max = 20 -> max - min = 15  -> range 0 - 15 + min -> 5 - 20
        return random.nextInt(maximumNumber - minimumNumber) + minimumNumber;
    }

}
