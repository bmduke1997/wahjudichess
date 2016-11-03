This file lists things that still need to be done.

* Write a board structure which supports undo & redo
* Implement piece movement and piece rendering
* Turn taking
* Implement the rules of the game as constraints for movement
* Add victory condition
* Implement the AI
* Model pieces in Blender and convert them to JavaFX vertices. [Chris]

## Note: How to examine Blender models using Python

In Blender, open up the Console and type:

    cube = bpy.data.meshes['Cube']  # replace Cube with name of your mesh

(cube.data.polygons[n].vertices[m] = x) contains just ints.  I bet these
ints point to the vertices in cube.data.vertices[x], which allows
you to access the real coordinates of the vertices organized by polygon.
Get the coords with cube.data.vertices[x].co

For more info, see:

  https://en.wikibooks.org/wiki/Blender_3D:_Noob_to_Pro/Advanced_Tutorials/Python_Scripting/Export_scripts

