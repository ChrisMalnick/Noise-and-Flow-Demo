# **Noise and Flow Demo**

#### Summary

This interactive Java applet visually demonstrates the concept of both smooth and random noise. Generated noise can be sampled for use in many different applications. In this program, a vector field is discretely transformed by sampling from a two-dimensional cross section of three-dimensional Perlin noise. The sampled cross section is offset by one step along the Z-axis every frame producing a smooth continuous transition. Particles randomly propagate the vector field and move around accordingly.

#### Media

![Noise and Flow Demo](/Media/Noise_and_Flow_Demo.gif "Noise and Flow Demo")

Below are some examples of different applications I created that utilize the same principles demonstrated in this program.

The following screenshots are of an OpenGL based application featuring particles whose colors vary over time as well as different functions for transforming the flow field and influencing the particle behavior.

![2D Flow](/Media/2D_Flow_1.png "2D Flow")
![2D Flow](/Media/2D_Flow_2.png "2D Flow")

The following was produced by an OpenGL based 3D application utilizing four-dimensional Simplex noise to create a temporal three-dimensional vector field.

![3D Flow](/Media/3D_Flow_1.gif "3D Flow")
![3D Flow](/Media/3D_Flow_2.png "3D Flow")
