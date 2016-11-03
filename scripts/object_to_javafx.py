# From Chris:
#
# Since JavaFX doesn't come with model loading functions, I made
# this as a substitute.
#
# This script can be used to convert a static Blender mesh into
# JavaFX code which can be inserted into our program.  This lets
# us basically use models for objects.
#
# To use it, make a model in Blender and then open the Python
# Console.  Copy+paste this function in, then run it like so:
#
#     object_to_javafx(bpy.data.objects['MyObject'])
#
# replacing the string 'MyObject' with the name of your model.
#
# Note!!!  It will only work on meshes with triangles instead of
# quads.  You need to hit Ctrl+T in edit mode in the 3D View in
# order to convert quads into tris.

def object_to_javafx(o):
    # add a dummy tex coords (we use colors, not textures!)
    print('mesh.getTexCoords().addAll(0, 0);')
    # first, add all the points
    print('float points[] = {', end='')
    num = 0
    for vertex in o.data.vertices:
        coords = vertex.co
        print(("(float)%.4f" % (-coords[0] * 5)) + ', ' + ("(float)%.4f" % (-coords[1] * 5)) + ', ' + ("(float)%.4f" % (-coords[2] * 5)), end='')
        if num != len(o.data.vertices) - 1:
            print(', ', end='')
        num += 1
    print('};')
    print('mesh.getPoints().addAll(points);')
    # then, add all the faces using those points' indices
    print('int faces[] = {', end='')
    num = 0
    for face in o.data.polygons:
        no = 0
        for index in list(reversed(face.vertices)):
            print(str(index) + ', 0', end='')
            if num != len(o.data.polygons) - 1 or no != len(face.vertices) - 1:
                print(', ', end='')
            no += 1
        num += 1
    print('};')
    print('mesh.getFaces().addAll(faces);')
