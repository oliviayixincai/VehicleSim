# Vehicle Simulation
This is a simulation of a world that has effects and interactions between pedestrians and vehicles. **See detailed features in each Class.**

Pedestrian
----------

Pedestrians will be randomly spawned from one side of the road and move to the other side.

Pedestrians can be knocked down if hit by Car or RaceCar, and healed by Ambulance except for Skeleton.

There are three types of pedestrians, Walkman, Child and Skeleton.

*   Walkman: walk staight, can be knocked down and healed.
*   Skeleton: walk straight, can be knocked down, cannot be healed, revive during halloween.
*   Child: walk with unpredicted changing angles(20 - 60), can be knocked down and healed.

Vehicle
-------

Vehicles, except for PoliceCar, will be randomly spawned at the beginning of the lane and move along the lane.

PoliceCar will appear in the same lane once a Car or RaceCar hits a pedestrian.

RaceCar and PoliceCar will change lane if nearby lane is clear when stuck behind a slower vehicle

Vehicles will slow down when they are stuck behind a slower vehicle.

Vehicles will have brake sound effect when slow down.

Vehicles will have honk sound effect before change lane.

Vehicles will increase speed during lane change.

There are five types of vehicles, Ambulance, Bus, Car, RaceCar, and PoliceCar.

*   Ambulance: heals Walkman and Child
*   Bus: pick up Walkman and Child and stop one second after
*   Car: can hit pedestrian
*   RaceCar: can hit pedestrian and increase speed after, can change lane
*   PoliceCar: high speed, can change lane

Effect
------

Effects will be randomly spawned and last for a short period.

Effects have auditory and/or visual effects.

There are three world-wide effects, Halloween, Moon and Rain.

Halloween
---------

A witch gif will appear on the top left of the world for a short period.

After witch disappear, halloween background will fade in and fade out.

After halloween backgroup fade in, revive all of the skeletons.

Set two rounds of bombs in random places.

Moon
----

At the top of the world, a moon gif will move from left to right.

Rise during the first half and fall during the second half.

Background image will be in dark mode.

Right after moon appears, all lanes, vihecles and vehicle spawners will change direction.

Rain
----

Two colored rain with fade in and fade out.

Rain density will gradually increase before fading out and decrease while fading out.

All vehicles will be slowed down during the rain effect.

Child and Walkman spawned during the effect will have penguin appearance.

Bomb
----

Bombs are traps to trigger explosions. If pedestrians or vihicles are in contact with a bomb, explosion will be triggered.

Explosion
---------

Explosions are triggered by bombs and will remove all of the pedestrians and vehicles in the explosion range, along with sound effect.

Volume Control
--------------

Lable and Buttons at the top right of the world.

Sounds
------

World background sound while not in effect

Halloween effect sound

Rain effect sound

Explosion sound

Ambulance sound

PoliceCar sound

RaceCare sound after hitting pedestrian

Honk sound before vehicles change lane

Brake sound when vehicles slow down

Checklist
=========

*   Some or all Vehicles change lanes when a slower vehicle is ahead via drive() method - **DONE**
*   Ambulance and Bus interactions implemented - **DONE**
*   An additional Vehicle class has been implemented with its own interactions - **DONE (RaceCar, PoliceCar)**
*   Pedestrian has been turned into an abstract superclass with at least two subclasses with unique behavior and/or traits - **DONE (Child, Skeleton)**
*   Added a Local, Collision- or Detection- triggered event or effect, that temporarily changes the speed (or some other attribute/behavior) of all Vehicles in the simulation through use of ArrayList and iterative loop - **DONE (Bomb, Rain, Skeleton, PoliceCar RaceCar, Moon, VolumeButton)**
*   Added a worldwide effect that affects all Vehicles of any given class (can be all Vehicles, all Ambulances, etc). through use of ArrayList and iterative loop - **DONE (Moon, Rain)**
*   Added (6) sound effects, as described - **DONE (ambient background music, halloween sound, rain sound, explosion sound, ambulance sound, race car acceleration sound, police car siren, honk sound, brake sound)**
*   Code uses inheritance appropriately to avoid redundancy, using abstract methods, superclass methods and/or other tools appropriately. - **DONE (I tried my best in my level)**
*   Basic simulation functions like spawning, object removal and movement work correctly. - **DONE (Haven't found any bugs yet)**
*   Code follows coding conventions: - **(I tried my best)**

*   commented appropriately (a level of detail to explain to someone at approximately your skill level) - **DONE**
*   Variables, methods and classes named correctly - **DONE**
*   Consistent indentation, consistent style. - **DONE**
*   Don't use public variables! - **DONE**
