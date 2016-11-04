package chess.gui;

import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

public class PawnMeshView extends MeshView {
    public PawnMeshView() {
        super();

        TriangleMesh mesh = new TriangleMesh();

        /* Add the dummy texture coordinates. */
        mesh.getTexCoords().addAll(0, 0);

        /* Add the points to the mesh. */
        float points[] = {(float)-0.0000, (float)-3.2139, (float)1.0320, (float)-0.0000, (float)-3.2139, (float)-0.2536, (float)-0.6270, (float)-3.1521, (float)1.0320, (float)-0.6270, (float)-3.1521, (float)-0.2536, (float)-1.2299, (float)-2.9692, (float)1.0320, (float)-1.2299, (float)-2.9692, (float)-0.2536, (float)-1.7855, (float)-2.6722, (float)1.0320, (float)-1.7855, (float)-2.6722, (float)-0.2536, (float)-2.2726, (float)-2.2726, (float)1.0320, (float)-2.2726, (float)-2.2726, (float)-0.2536, (float)-2.6722, (float)-1.7855, (float)1.0320, (float)-2.6722, (float)-1.7855, (float)-0.2536, (float)-2.9692, (float)-1.2299, (float)1.0320, (float)-2.9692, (float)-1.2299, (float)-0.2536, (float)-3.1521, (float)-0.6270, (float)1.0320, (float)-3.1521, (float)-0.6270, (float)-0.2536, (float)-3.2139, (float)-0.0000, (float)1.0320, (float)-3.2139, (float)-0.0000, (float)-0.2536, (float)-3.1521, (float)0.6270, (float)1.0320, (float)-3.1521, (float)0.6270, (float)-0.2536, (float)-2.9692, (float)1.2299, (float)1.0320, (float)-2.9692, (float)1.2299, (float)-0.2536, (float)-2.6722, (float)1.7855, (float)1.0320, (float)-2.6722, (float)1.7855, (float)-0.2536, (float)-2.2726, (float)2.2726, (float)1.0320, (float)-2.2726, (float)2.2726, (float)-0.2536, (float)-1.7855, (float)2.6722, (float)1.0320, (float)-1.7855, (float)2.6722, (float)-0.2536, (float)-1.2299, (float)2.9692, (float)1.0320, (float)-1.2299, (float)2.9692, (float)-0.2536, (float)-0.6270, (float)3.1521, (float)1.0320, (float)-0.6270, (float)3.1521, (float)-0.2536, (float)0.0000, (float)3.2139, (float)1.0320, (float)0.0000, (float)3.2139, (float)-0.2536, (float)0.6270, (float)3.1521, (float)1.0320, (float)0.6270, (float)3.1521, (float)-0.2536, (float)1.2299, (float)2.9692, (float)1.0320, (float)1.2299, (float)2.9692, (float)-0.2536, (float)1.7855, (float)2.6722, (float)1.0320, (float)1.7855, (float)2.6722, (float)-0.2536, (float)2.2726, (float)2.2726, (float)1.0320, (float)2.2726, (float)2.2726, (float)-0.2536, (float)2.6722, (float)1.7855, (float)1.0320, (float)2.6722, (float)1.7855, (float)-0.2536, (float)2.9692, (float)1.2299, (float)1.0320, (float)2.9692, (float)1.2299, (float)-0.2536, (float)3.1521, (float)0.6270, (float)1.0320, (float)3.1521, (float)0.6270, (float)-0.2536, (float)3.2139, (float)-0.0000, (float)1.0320, (float)3.2139, (float)-0.0000, (float)-0.2536, (float)3.1521, (float)-0.6270, (float)1.0320, (float)3.1521, (float)-0.6270, (float)-0.2536, (float)2.9692, (float)-1.2299, (float)1.0320, (float)2.9692, (float)-1.2299, (float)-0.2536, (float)2.6722, (float)-1.7855, (float)1.0320, (float)2.6722, (float)-1.7855, (float)-0.2536, (float)2.2726, (float)-2.2726, (float)1.0320, (float)2.2726, (float)-2.2726, (float)-0.2536, (float)1.7855, (float)-2.6722, (float)1.0320, (float)1.7855, (float)-2.6722, (float)-0.2536, (float)1.2299, (float)-2.9692, (float)1.0320, (float)1.2299, (float)-2.9692, (float)-0.2536, (float)0.6270, (float)-3.1521, (float)1.0320, (float)0.6270, (float)-3.1521, (float)-0.2536, (float)1.5914, (float)1.5914, (float)-0.1287, (float)0.7641, (float)0.7641, (float)-4.1629, (float)1.5914, (float)-1.5914, (float)-0.1287, (float)0.7641, (float)-0.7641, (float)-4.1629, (float)-1.5914, (float)1.5914, (float)-0.1287, (float)-0.7641, (float)0.7641, (float)-4.1629, (float)-1.5914, (float)-1.5914, (float)-0.1287, (float)-0.7641, (float)-0.7641, (float)-4.1629, (float)-0.0000, (float)0.0000, (float)-3.4369, (float)-1.3192, (float)0.9584, (float)-4.7143, (float)0.5039, (float)1.5508, (float)-4.7143, (float)1.6306, (float)0.0000, (float)-4.7143, (float)0.5039, (float)-1.5508, (float)-4.7143, (float)-1.3192, (float)-0.9584, (float)-4.7143, (float)-0.5039, (float)1.5508, (float)-6.7811, (float)1.3192, (float)0.9584, (float)-6.7811, (float)1.3192, (float)-0.9584, (float)-6.7811, (float)-0.5039, (float)-1.5508, (float)-6.7811, (float)-1.6306, (float)0.0000, (float)-6.7811, (float)-0.0000, (float)0.0000, (float)-8.0584, (float)0.2962, (float)0.9115, (float)-3.7820, (float)-0.7754, (float)0.5634, (float)-3.7820, (float)-0.4792, (float)1.4749, (float)-4.5328, (float)-1.5508, (float)0.0000, (float)-4.5328, (float)-0.7754, (float)-0.5634, (float)-3.7820, (float)0.9584, (float)0.0000, (float)-3.7820, (float)1.2546, (float)0.9115, (float)-4.5328, (float)0.2962, (float)-0.9115, (float)-3.7820, (float)1.2546, (float)-0.9115, (float)-4.5328, (float)-0.4792, (float)-1.4749, (float)-4.5328, (float)-1.7339, (float)0.5634, (float)-5.7477, (float)-1.7339, (float)-0.5634, (float)-5.7477, (float)-0.0000, (float)1.8231, (float)-5.7477, (float)-1.0716, (float)1.4749, (float)-5.7477, (float)1.7339, (float)0.5634, (float)-5.7477, (float)1.0716, (float)1.4749, (float)-5.7477, (float)1.0716, (float)-1.4749, (float)-5.7477, (float)1.7339, (float)-0.5634, (float)-5.7477, (float)-1.0716, (float)-1.4749, (float)-5.7477, (float)-0.0000, (float)-1.8231, (float)-5.7477, (float)-1.2546, (float)0.9115, (float)-6.9625, (float)0.4792, (float)1.4749, (float)-6.9625, (float)1.5508, (float)0.0000, (float)-6.9625, (float)0.4792, (float)-1.4749, (float)-6.9625, (float)-1.2546, (float)-0.9115, (float)-6.9625, (float)-0.2962, (float)0.9115, (float)-7.7133, (float)-0.9584, (float)0.0000, (float)-7.7133, (float)0.7754, (float)0.5634, (float)-7.7133, (float)0.7754, (float)-0.5634, (float)-7.7133, (float)-0.2962, (float)-0.9115, (float)-7.7133};
        mesh.getPoints().addAll(points);

        /* Add the faces to the mesh. */
        int faces[] = {0, 0, 2, 0, 1, 0, 2, 0, 4, 0, 3, 0, 4, 0, 6, 0, 5, 0, 6, 0, 8, 0, 7, 0, 8, 0, 10, 0, 9, 0, 10, 0, 12, 0, 11, 0, 12, 0, 14, 0, 13, 0, 14, 0, 16, 0, 15, 0, 16, 0, 18, 0, 17, 0, 18, 0, 20, 0, 19, 0, 20, 0, 22, 0, 21, 0, 22, 0, 24, 0, 23, 0, 24, 0, 26, 0, 25, 0, 26, 0, 28, 0, 27, 0, 28, 0, 30, 0, 29, 0, 30, 0, 32, 0, 31, 0, 32, 0, 34, 0, 33, 0, 34, 0, 36, 0, 35, 0, 36, 0, 38, 0, 37, 0, 38, 0, 40, 0, 39, 0, 40, 0, 42, 0, 41, 0, 42, 0, 44, 0, 43, 0, 44, 0, 46, 0, 45, 0, 46, 0, 48, 0, 47, 0, 48, 0, 50, 0, 49, 0, 50, 0, 52, 0, 51, 0, 52, 0, 54, 0, 53, 0, 54, 0, 56, 0, 55, 0, 56, 0, 58, 0, 57, 0, 58, 0, 60, 0, 59, 0, 53, 0, 21, 0, 37, 0, 60, 0, 62, 0, 61, 0, 62, 0, 0, 0, 63, 0, 62, 0, 46, 0, 30, 0, 64, 0, 66, 0, 65, 0, 66, 0, 70, 0, 67, 0, 70, 0, 68, 0, 71, 0, 68, 0, 64, 0, 69, 0, 66, 0, 64, 0, 70, 0, 71, 0, 69, 0, 67, 0, 84, 0, 85, 0, 72, 0, 87, 0, 85, 0, 73, 0, 89, 0, 84, 0, 72, 0, 91, 0, 89, 0, 72, 0, 88, 0, 91, 0, 72, 0, 94, 0, 87, 0, 73, 0, 96, 0, 86, 0, 74, 0, 98, 0, 90, 0, 75, 0, 100, 0, 92, 0, 76, 0, 102, 0, 93, 0, 77, 0, 97, 0, 94, 0, 73, 0, 99, 0, 96, 0, 74, 0, 101, 0, 98, 0, 75, 0, 103, 0, 100, 0, 76, 0, 95, 0, 102, 0, 77, 0, 109, 0, 104, 0, 78, 0, 111, 0, 105, 0, 79, 0, 112, 0, 106, 0, 80, 0, 113, 0, 107, 0, 81, 0, 110, 0, 108, 0, 82, 0, 83, 0, 113, 0, 110, 0, 113, 0, 108, 0, 110, 0, 113, 0, 81, 0, 108, 0, 83, 0, 112, 0, 113, 0, 112, 0, 107, 0, 113, 0, 112, 0, 80, 0, 107, 0, 83, 0, 111, 0, 112, 0, 111, 0, 106, 0, 112, 0, 111, 0, 79, 0, 106, 0, 83, 0, 109, 0, 111, 0, 109, 0, 105, 0, 111, 0, 109, 0, 78, 0, 105, 0, 83, 0, 110, 0, 109, 0, 110, 0, 104, 0, 109, 0, 110, 0, 82, 0, 104, 0, 82, 0, 108, 0, 95, 0, 108, 0, 102, 0, 95, 0, 108, 0, 81, 0, 102, 0, 81, 0, 107, 0, 103, 0, 107, 0, 100, 0, 103, 0, 107, 0, 80, 0, 100, 0, 80, 0, 106, 0, 101, 0, 106, 0, 98, 0, 101, 0, 106, 0, 79, 0, 98, 0, 79, 0, 105, 0, 99, 0, 105, 0, 96, 0, 99, 0, 105, 0, 78, 0, 96, 0, 78, 0, 104, 0, 97, 0, 104, 0, 94, 0, 97, 0, 104, 0, 82, 0, 94, 0, 81, 0, 103, 0, 102, 0, 103, 0, 93, 0, 102, 0, 103, 0, 76, 0, 93, 0, 80, 0, 101, 0, 100, 0, 101, 0, 92, 0, 100, 0, 101, 0, 75, 0, 92, 0, 79, 0, 99, 0, 98, 0, 99, 0, 90, 0, 98, 0, 99, 0, 74, 0, 90, 0, 78, 0, 97, 0, 96, 0, 97, 0, 86, 0, 96, 0, 97, 0, 73, 0, 86, 0, 82, 0, 95, 0, 94, 0, 95, 0, 87, 0, 94, 0, 95, 0, 77, 0, 87, 0, 77, 0, 93, 0, 88, 0, 93, 0, 91, 0, 88, 0, 93, 0, 76, 0, 91, 0, 76, 0, 92, 0, 91, 0, 92, 0, 89, 0, 91, 0, 92, 0, 75, 0, 89, 0, 75, 0, 90, 0, 89, 0, 90, 0, 84, 0, 89, 0, 90, 0, 74, 0, 84, 0, 77, 0, 88, 0, 87, 0, 88, 0, 85, 0, 87, 0, 88, 0, 72, 0, 85, 0, 74, 0, 86, 0, 84, 0, 86, 0, 85, 0, 84, 0, 86, 0, 73, 0, 85, 0, 2, 0, 3, 0, 1, 0, 4, 0, 5, 0, 3, 0, 6, 0, 7, 0, 5, 0, 8, 0, 9, 0, 7, 0, 10, 0, 11, 0, 9, 0, 12, 0, 13, 0, 11, 0, 14, 0, 15, 0, 13, 0, 16, 0, 17, 0, 15, 0, 18, 0, 19, 0, 17, 0, 20, 0, 21, 0, 19, 0, 22, 0, 23, 0, 21, 0, 24, 0, 25, 0, 23, 0, 26, 0, 27, 0, 25, 0, 28, 0, 29, 0, 27, 0, 30, 0, 31, 0, 29, 0, 32, 0, 33, 0, 31, 0, 34, 0, 35, 0, 33, 0, 36, 0, 37, 0, 35, 0, 38, 0, 39, 0, 37, 0, 40, 0, 41, 0, 39, 0, 42, 0, 43, 0, 41, 0, 44, 0, 45, 0, 43, 0, 46, 0, 47, 0, 45, 0, 48, 0, 49, 0, 47, 0, 50, 0, 51, 0, 49, 0, 52, 0, 53, 0, 51, 0, 54, 0, 55, 0, 53, 0, 56, 0, 57, 0, 55, 0, 58, 0, 59, 0, 57, 0, 60, 0, 61, 0, 59, 0, 1, 0, 3, 0, 5, 0, 61, 0, 63, 0, 1, 0, 53, 0, 59, 0, 61, 0, 53, 0, 55, 0, 57, 0, 49, 0, 51, 0, 53, 0, 45, 0, 47, 0, 49, 0, 37, 0, 43, 0, 45, 0, 37, 0, 39, 0, 41, 0, 29, 0, 35, 0, 37, 0, 29, 0, 31, 0, 33, 0, 25, 0, 27, 0, 29, 0, 21, 0, 23, 0, 25, 0, 17, 0, 19, 0, 21, 0, 13, 0, 15, 0, 17, 0, 9, 0, 11, 0, 13, 0, 5, 0, 7, 0, 9, 0, 53, 0, 1, 0, 5, 0, 53, 0, 57, 0, 59, 0, 37, 0, 49, 0, 53, 0, 37, 0, 41, 0, 43, 0, 29, 0, 33, 0, 35, 0, 37, 0, 25, 0, 29, 0, 13, 0, 17, 0, 21, 0, 21, 0, 9, 0, 13, 0, 53, 0, 61, 0, 1, 0, 37, 0, 45, 0, 49, 0, 21, 0, 25, 0, 37, 0, 5, 0, 9, 0, 21, 0, 21, 0, 53, 0, 5, 0, 62, 0, 63, 0, 61, 0, 0, 0, 1, 0, 63, 0, 6, 0, 0, 0, 62, 0, 6, 0, 4, 0, 2, 0, 10, 0, 8, 0, 6, 0, 6, 0, 12, 0, 10, 0, 22, 0, 16, 0, 14, 0, 22, 0, 20, 0, 18, 0, 26, 0, 24, 0, 22, 0, 30, 0, 28, 0, 26, 0, 34, 0, 32, 0, 30, 0, 38, 0, 36, 0, 34, 0, 42, 0, 40, 0, 38, 0, 46, 0, 44, 0, 42, 0, 54, 0, 48, 0, 46, 0, 54, 0, 52, 0, 50, 0, 58, 0, 56, 0, 54, 0, 54, 0, 60, 0, 58, 0, 6, 0, 2, 0, 0, 0, 14, 0, 12, 0, 6, 0, 22, 0, 18, 0, 16, 0, 14, 0, 26, 0, 22, 0, 46, 0, 34, 0, 30, 0, 46, 0, 42, 0, 38, 0, 54, 0, 50, 0, 48, 0, 62, 0, 60, 0, 54, 0, 14, 0, 6, 0, 62, 0, 30, 0, 26, 0, 14, 0, 46, 0, 38, 0, 34, 0, 62, 0, 54, 0, 46, 0, 30, 0, 14, 0, 62, 0, 66, 0, 67, 0, 65, 0, 70, 0, 71, 0, 67, 0, 68, 0, 69, 0, 71, 0, 64, 0, 65, 0, 69, 0, 64, 0, 68, 0, 70, 0, 69, 0, 65, 0, 67, 0};
        mesh.getFaces().addAll(faces);

        setMesh(mesh);
    }
}