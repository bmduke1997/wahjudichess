package chess.gui;

import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

public class QueenMeshView extends MeshView {
    private static TriangleMesh mesh = null;

    public QueenMeshView() {
        super();

        /* If the mesh has not already been initialized,
         * initialize it.  Else, use the one we've already made. */
        if (mesh == null) {
            mesh = new TriangleMesh();

            /* Add the dummy texture coordinates. */
            mesh.getTexCoords().addAll(0, 0);

            /* Add the points to the mesh. */
            mesh.getTexCoords().addAll(0, 0);
            float points[] = {(float)-0.0000, (float)-3.2139, (float)-0.0672, (float)-0.0000, (float)-3.2139, (float)-1.3527, (float)-0.6270, (float)-3.1521, (float)-0.0672, (float)-0.6270, (float)-3.1521, (float)-1.3527, (float)-1.2299, (float)-2.9692, (float)-0.0672, (float)-1.2299, (float)-2.9692, (float)-1.3527, (float)-1.7855, (float)-2.6722, (float)-0.0672, (float)-1.7855, (float)-2.6722, (float)-1.3527, (float)-2.2726, (float)-2.2726, (float)-0.0672, (float)-2.2726, (float)-2.2726, (float)-1.3527, (float)-2.6722, (float)-1.7855, (float)-0.0672, (float)-2.6722, (float)-1.7855, (float)-1.3527, (float)-2.9692, (float)-1.2299, (float)-0.0672, (float)-2.9692, (float)-1.2299, (float)-1.3527, (float)-3.1521, (float)-0.6270, (float)-0.0672, (float)-3.1521, (float)-0.6270, (float)-1.3527, (float)-3.2139, (float)-0.0000, (float)-0.0672, (float)-3.2139, (float)-0.0000, (float)-1.3527, (float)-3.1521, (float)0.6270, (float)-0.0672, (float)-3.1521, (float)0.6270, (float)-1.3527, (float)-2.9692, (float)1.2299, (float)-0.0672, (float)-2.9692, (float)1.2299, (float)-1.3527, (float)-2.6722, (float)1.7855, (float)-0.0672, (float)-2.6722, (float)1.7855, (float)-1.3527, (float)-2.2726, (float)2.2726, (float)-0.0672, (float)-2.2726, (float)2.2726, (float)-1.3527, (float)-1.7855, (float)2.6722, (float)-0.0672, (float)-1.7855, (float)2.6722, (float)-1.3527, (float)-1.2299, (float)2.9692, (float)-0.0672, (float)-1.2299, (float)2.9692, (float)-1.3527, (float)-0.6270, (float)3.1521, (float)-0.0672, (float)-0.6270, (float)3.1521, (float)-1.3527, (float)0.0000, (float)3.2139, (float)-0.0672, (float)0.0000, (float)3.2139, (float)-1.3527, (float)0.6270, (float)3.1521, (float)-0.0672, (float)0.6270, (float)3.1521, (float)-1.3527, (float)1.2299, (float)2.9692, (float)-0.0672, (float)1.2299, (float)2.9692, (float)-1.3527, (float)1.7855, (float)2.6722, (float)-0.0672, (float)1.7855, (float)2.6722, (float)-1.3527, (float)2.2726, (float)2.2726, (float)-0.0672, (float)2.2726, (float)2.2726, (float)-1.3527, (float)2.6722, (float)1.7855, (float)-0.0672, (float)2.6722, (float)1.7855, (float)-1.3527, (float)2.9692, (float)1.2299, (float)-0.0672, (float)2.9692, (float)1.2299, (float)-1.3527, (float)3.1521, (float)0.6270, (float)-0.0672, (float)3.1521, (float)0.6270, (float)-1.3527, (float)3.2139, (float)-0.0000, (float)-0.0672, (float)3.2139, (float)-0.0000, (float)-1.3527, (float)3.1521, (float)-0.6270, (float)-0.0672, (float)3.1521, (float)-0.6270, (float)-1.3527, (float)2.9692, (float)-1.2299, (float)-0.0672, (float)2.9692, (float)-1.2299, (float)-1.3527, (float)2.6722, (float)-1.7855, (float)-0.0672, (float)2.6722, (float)-1.7855, (float)-1.3527, (float)2.2726, (float)-2.2726, (float)-0.0672, (float)2.2726, (float)-2.2726, (float)-1.3527, (float)1.7855, (float)-2.6722, (float)-0.0672, (float)1.7855, (float)-2.6722, (float)-1.3527, (float)1.2299, (float)-2.9692, (float)-0.0672, (float)1.2299, (float)-2.9692, (float)-1.3527, (float)0.6270, (float)-3.1521, (float)-0.0672, (float)0.6270, (float)-3.1521, (float)-1.3527, (float)1.5914, (float)1.5914, (float)-1.2278, (float)0.7641, (float)0.7641, (float)-6.9309, (float)1.5914, (float)-1.5914, (float)-1.2278, (float)0.7641, (float)-0.7641, (float)-6.9309, (float)-1.5914, (float)1.5914, (float)-1.2278, (float)-0.7641, (float)0.7641, (float)-6.9309, (float)-1.5914, (float)-1.5914, (float)-1.2278, (float)-0.7641, (float)-0.7641, (float)-6.9309, (float)-0.0000, (float)-0.0000, (float)-8.2787, (float)-0.3341, (float)0.2427, (float)-8.6022, (float)0.1276, (float)0.3927, (float)-8.6022, (float)0.4129, (float)-0.0000, (float)-8.6022, (float)0.1276, (float)-0.3927, (float)-8.6022, (float)-0.3341, (float)-0.2427, (float)-8.6022, (float)-0.1276, (float)0.3927, (float)-9.1256, (float)0.3341, (float)0.2427, (float)-9.1256, (float)0.3341, (float)-0.2427, (float)-9.1256, (float)-0.1276, (float)-0.3927, (float)-9.1256, (float)-0.4129, (float)-0.0000, (float)-9.1256, (float)-0.0000, (float)-0.0000, (float)-9.4491, (float)0.0750, (float)0.2308, (float)-8.3661, (float)-0.1964, (float)0.1427, (float)-8.3661, (float)-0.1214, (float)0.3735, (float)-8.5562, (float)-0.3927, (float)-0.0000, (float)-8.5562, (float)-0.1964, (float)-0.1427, (float)-8.3661, (float)0.2427, (float)-0.0000, (float)-8.3661, (float)0.3177, (float)0.2308, (float)-8.5562, (float)0.0750, (float)-0.2308, (float)-8.3661, (float)0.3177, (float)-0.2308, (float)-8.5562, (float)-0.1214, (float)-0.3735, (float)-8.5562, (float)-0.4391, (float)0.1427, (float)-8.8639, (float)-0.4391, (float)-0.1427, (float)-8.8639, (float)-0.0000, (float)0.4617, (float)-8.8639, (float)-0.2714, (float)0.3735, (float)-8.8639, (float)0.4391, (float)0.1427, (float)-8.8639, (float)0.2714, (float)0.3735, (float)-8.8639, (float)0.2714, (float)-0.3735, (float)-8.8639, (float)0.4391, (float)-0.1427, (float)-8.8639, (float)-0.2714, (float)-0.3735, (float)-8.8639, (float)-0.0000, (float)-0.4617, (float)-8.8639, (float)-0.3177, (float)0.2308, (float)-9.1715, (float)0.1214, (float)0.3735, (float)-9.1715, (float)0.3927, (float)-0.0000, (float)-9.1715, (float)0.1214, (float)-0.3735, (float)-9.1715, (float)-0.3177, (float)-0.2308, (float)-9.1715, (float)-0.0750, (float)0.2308, (float)-9.3617, (float)-0.2427, (float)-0.0000, (float)-9.3617, (float)0.1964, (float)0.1427, (float)-9.3617, (float)0.1964, (float)-0.1427, (float)-9.3617, (float)-0.0750, (float)-0.2308, (float)-9.3617, (float)-0.0036, (float)-0.7922, (float)-6.6236, (float)-0.0036, (float)-1.5208, (float)-8.6001, (float)-0.1557, (float)-0.7772, (float)-6.6236, (float)-0.2978, (float)-1.4919, (float)-8.6001, (float)-0.3019, (float)-0.7328, (float)-6.6236, (float)-0.5808, (float)-1.4060, (float)-8.6001, (float)-0.4367, (float)-0.6608, (float)-6.6236, (float)-0.8415, (float)-1.2666, (float)-8.6001, (float)-0.5549, (float)-0.5638, (float)-6.6236, (float)-1.0701, (float)-1.0791, (float)-8.6001, (float)-0.6518, (float)-0.4457, (float)-6.6236, (float)-1.2577, (float)-0.8505, (float)-8.6001, (float)-0.7239, (float)-0.3109, (float)-6.6236, (float)-1.3971, (float)-0.5897, (float)-8.6001, (float)-0.7682, (float)-0.1646, (float)-6.6236, (float)-1.4829, (float)-0.3068, (float)-8.6001, (float)-0.7832, (float)-0.0125, (float)-6.6236, (float)-1.5119, (float)-0.0125, (float)-8.6001, (float)-0.7682, (float)0.1396, (float)-6.6236, (float)-1.4829, (float)0.2817, (float)-8.6001, (float)-0.7239, (float)0.2858, (float)-6.6236, (float)-1.3971, (float)0.5647, (float)-8.6001, (float)-0.6518, (float)0.4206, (float)-6.6236, (float)-1.2577, (float)0.8255, (float)-8.6001, (float)-0.5549, (float)0.5388, (float)-6.6236, (float)-1.0701, (float)1.0540, (float)-8.6001, (float)-0.4367, (float)0.6357, (float)-6.6236, (float)-0.8415, (float)1.2416, (float)-8.6001, (float)-0.3019, (float)0.7078, (float)-6.6236, (float)-0.5808, (float)1.3810, (float)-8.6001, (float)-0.1557, (float)0.7522, (float)-6.6236, (float)-0.2978, (float)1.4668, (float)-8.6001, (float)-0.0036, (float)0.7671, (float)-6.6236, (float)-0.0036, (float)1.4958, (float)-8.6001, (float)0.1485, (float)0.7522, (float)-6.6236, (float)0.2907, (float)1.4668, (float)-8.6001, (float)0.2948, (float)0.7078, (float)-6.6236, (float)0.5736, (float)1.3810, (float)-8.6001, (float)0.4296, (float)0.6357, (float)-6.6236, (float)0.8344, (float)1.2416, (float)-8.6001, (float)0.5477, (float)0.5388, (float)-6.6236, (float)1.0630, (float)1.0540, (float)-8.6001, (float)0.6447, (float)0.4206, (float)-6.6236, (float)1.2506, (float)0.8255, (float)-8.6001, (float)0.7167, (float)0.2858, (float)-6.6236, (float)1.3900, (float)0.5647, (float)-8.6001, (float)0.7611, (float)0.1396, (float)-6.6236, (float)1.4758, (float)0.2817, (float)-8.6001, (float)0.7761, (float)-0.0125, (float)-6.6236, (float)1.5048, (float)-0.0125, (float)-8.6001, (float)0.7611, (float)-0.1646, (float)-6.6236, (float)1.4758, (float)-0.3068, (float)-8.6001, (float)0.7167, (float)-0.3109, (float)-6.6236, (float)1.3899, (float)-0.5897, (float)-8.6001, (float)0.6447, (float)-0.4457, (float)-6.6236, (float)1.2506, (float)-0.8505, (float)-8.6001, (float)0.5477, (float)-0.5638, (float)-6.6236, (float)1.0630, (float)-1.0791, (float)-8.6001, (float)0.4296, (float)-0.6608, (float)-6.6236, (float)0.8344, (float)-1.2667, (float)-8.6001, (float)0.2948, (float)-0.7328, (float)-6.6236, (float)0.5736, (float)-1.4060, (float)-8.6001, (float)0.1485, (float)-0.7772, (float)-6.6236, (float)0.2907, (float)-1.4919, (float)-8.6001};
            mesh.getPoints().addAll(points);

            /* Add the faces to the mesh. */
            int faces[] = {0, 0, 2, 0, 1, 0, 2, 0, 4, 0, 3, 0, 4, 0, 6, 0, 5, 0, 6, 0, 8, 0, 7, 0, 8, 0, 10, 0, 9, 0, 10, 0, 12, 0, 11, 0, 12, 0, 14, 0, 13, 0, 14, 0, 16, 0, 15, 0, 16, 0, 18, 0, 17, 0, 18, 0, 20, 0, 19, 0, 20, 0, 22, 0, 21, 0, 22, 0, 24, 0, 23, 0, 24, 0, 26, 0, 25, 0, 26, 0, 28, 0, 27, 0, 28, 0, 30, 0, 29, 0, 30, 0, 32, 0, 31, 0, 32, 0, 34, 0, 33, 0, 34, 0, 36, 0, 35, 0, 36, 0, 38, 0, 37, 0, 38, 0, 40, 0, 39, 0, 40, 0, 42, 0, 41, 0, 42, 0, 44, 0, 43, 0, 44, 0, 46, 0, 45, 0, 46, 0, 48, 0, 47, 0, 48, 0, 50, 0, 49, 0, 50, 0, 52, 0, 51, 0, 52, 0, 54, 0, 53, 0, 54, 0, 56, 0, 55, 0, 56, 0, 58, 0, 57, 0, 58, 0, 60, 0, 59, 0, 53, 0, 21, 0, 37, 0, 60, 0, 62, 0, 61, 0, 62, 0, 0, 0, 63, 0, 62, 0, 46, 0, 30, 0, 64, 0, 66, 0, 65, 0, 66, 0, 70, 0, 67, 0, 70, 0, 68, 0, 71, 0, 68, 0, 64, 0, 69, 0, 66, 0, 64, 0, 70, 0, 71, 0, 69, 0, 67, 0, 84, 0, 85, 0, 72, 0, 87, 0, 85, 0, 73, 0, 89, 0, 84, 0, 72, 0, 91, 0, 89, 0, 72, 0, 88, 0, 91, 0, 72, 0, 94, 0, 87, 0, 73, 0, 96, 0, 86, 0, 74, 0, 98, 0, 90, 0, 75, 0, 100, 0, 92, 0, 76, 0, 102, 0, 93, 0, 77, 0, 97, 0, 94, 0, 73, 0, 99, 0, 96, 0, 74, 0, 101, 0, 98, 0, 75, 0, 103, 0, 100, 0, 76, 0, 95, 0, 102, 0, 77, 0, 109, 0, 104, 0, 78, 0, 111, 0, 105, 0, 79, 0, 112, 0, 106, 0, 80, 0, 113, 0, 107, 0, 81, 0, 110, 0, 108, 0, 82, 0, 83, 0, 113, 0, 110, 0, 113, 0, 108, 0, 110, 0, 113, 0, 81, 0, 108, 0, 83, 0, 112, 0, 113, 0, 112, 0, 107, 0, 113, 0, 112, 0, 80, 0, 107, 0, 83, 0, 111, 0, 112, 0, 111, 0, 106, 0, 112, 0, 111, 0, 79, 0, 106, 0, 83, 0, 109, 0, 111, 0, 109, 0, 105, 0, 111, 0, 109, 0, 78, 0, 105, 0, 83, 0, 110, 0, 109, 0, 110, 0, 104, 0, 109, 0, 110, 0, 82, 0, 104, 0, 82, 0, 108, 0, 95, 0, 108, 0, 102, 0, 95, 0, 108, 0, 81, 0, 102, 0, 81, 0, 107, 0, 103, 0, 107, 0, 100, 0, 103, 0, 107, 0, 80, 0, 100, 0, 80, 0, 106, 0, 101, 0, 106, 0, 98, 0, 101, 0, 106, 0, 79, 0, 98, 0, 79, 0, 105, 0, 99, 0, 105, 0, 96, 0, 99, 0, 105, 0, 78, 0, 96, 0, 78, 0, 104, 0, 97, 0, 104, 0, 94, 0, 97, 0, 104, 0, 82, 0, 94, 0, 81, 0, 103, 0, 102, 0, 103, 0, 93, 0, 102, 0, 103, 0, 76, 0, 93, 0, 80, 0, 101, 0, 100, 0, 101, 0, 92, 0, 100, 0, 101, 0, 75, 0, 92, 0, 79, 0, 99, 0, 98, 0, 99, 0, 90, 0, 98, 0, 99, 0, 74, 0, 90, 0, 78, 0, 97, 0, 96, 0, 97, 0, 86, 0, 96, 0, 97, 0, 73, 0, 86, 0, 82, 0, 95, 0, 94, 0, 95, 0, 87, 0, 94, 0, 95, 0, 77, 0, 87, 0, 77, 0, 93, 0, 88, 0, 93, 0, 91, 0, 88, 0, 93, 0, 76, 0, 91, 0, 76, 0, 92, 0, 91, 0, 92, 0, 89, 0, 91, 0, 92, 0, 75, 0, 89, 0, 75, 0, 90, 0, 89, 0, 90, 0, 84, 0, 89, 0, 90, 0, 74, 0, 84, 0, 77, 0, 88, 0, 87, 0, 88, 0, 85, 0, 87, 0, 88, 0, 72, 0, 85, 0, 74, 0, 86, 0, 84, 0, 86, 0, 85, 0, 84, 0, 86, 0, 73, 0, 85, 0, 2, 0, 3, 0, 1, 0, 4, 0, 5, 0, 3, 0, 6, 0, 7, 0, 5, 0, 8, 0, 9, 0, 7, 0, 10, 0, 11, 0, 9, 0, 12, 0, 13, 0, 11, 0, 14, 0, 15, 0, 13, 0, 16, 0, 17, 0, 15, 0, 18, 0, 19, 0, 17, 0, 20, 0, 21, 0, 19, 0, 22, 0, 23, 0, 21, 0, 24, 0, 25, 0, 23, 0, 26, 0, 27, 0, 25, 0, 28, 0, 29, 0, 27, 0, 30, 0, 31, 0, 29, 0, 32, 0, 33, 0, 31, 0, 34, 0, 35, 0, 33, 0, 36, 0, 37, 0, 35, 0, 38, 0, 39, 0, 37, 0, 40, 0, 41, 0, 39, 0, 42, 0, 43, 0, 41, 0, 44, 0, 45, 0, 43, 0, 46, 0, 47, 0, 45, 0, 48, 0, 49, 0, 47, 0, 50, 0, 51, 0, 49, 0, 52, 0, 53, 0, 51, 0, 54, 0, 55, 0, 53, 0, 56, 0, 57, 0, 55, 0, 58, 0, 59, 0, 57, 0, 60, 0, 61, 0, 59, 0, 1, 0, 3, 0, 5, 0, 61, 0, 63, 0, 1, 0, 53, 0, 59, 0, 61, 0, 53, 0, 55, 0, 57, 0, 49, 0, 51, 0, 53, 0, 45, 0, 47, 0, 49, 0, 37, 0, 43, 0, 45, 0, 37, 0, 39, 0, 41, 0, 29, 0, 35, 0, 37, 0, 29, 0, 31, 0, 33, 0, 25, 0, 27, 0, 29, 0, 21, 0, 23, 0, 25, 0, 17, 0, 19, 0, 21, 0, 13, 0, 15, 0, 17, 0, 9, 0, 11, 0, 13, 0, 5, 0, 7, 0, 9, 0, 53, 0, 1, 0, 5, 0, 53, 0, 57, 0, 59, 0, 37, 0, 49, 0, 53, 0, 37, 0, 41, 0, 43, 0, 29, 0, 33, 0, 35, 0, 37, 0, 25, 0, 29, 0, 13, 0, 17, 0, 21, 0, 21, 0, 9, 0, 13, 0, 53, 0, 61, 0, 1, 0, 37, 0, 45, 0, 49, 0, 21, 0, 25, 0, 37, 0, 5, 0, 9, 0, 21, 0, 21, 0, 53, 0, 5, 0, 62, 0, 63, 0, 61, 0, 0, 0, 1, 0, 63, 0, 6, 0, 0, 0, 62, 0, 6, 0, 4, 0, 2, 0, 10, 0, 8, 0, 6, 0, 6, 0, 12, 0, 10, 0, 22, 0, 16, 0, 14, 0, 22, 0, 20, 0, 18, 0, 26, 0, 24, 0, 22, 0, 30, 0, 28, 0, 26, 0, 34, 0, 32, 0, 30, 0, 38, 0, 36, 0, 34, 0, 42, 0, 40, 0, 38, 0, 46, 0, 44, 0, 42, 0, 54, 0, 48, 0, 46, 0, 54, 0, 52, 0, 50, 0, 58, 0, 56, 0, 54, 0, 54, 0, 60, 0, 58, 0, 6, 0, 2, 0, 0, 0, 14, 0, 12, 0, 6, 0, 22, 0, 18, 0, 16, 0, 14, 0, 26, 0, 22, 0, 46, 0, 34, 0, 30, 0, 46, 0, 42, 0, 38, 0, 54, 0, 50, 0, 48, 0, 62, 0, 60, 0, 54, 0, 14, 0, 6, 0, 62, 0, 30, 0, 26, 0, 14, 0, 46, 0, 38, 0, 34, 0, 62, 0, 54, 0, 46, 0, 30, 0, 14, 0, 62, 0, 66, 0, 67, 0, 65, 0, 70, 0, 71, 0, 67, 0, 68, 0, 69, 0, 71, 0, 64, 0, 65, 0, 69, 0, 64, 0, 68, 0, 70, 0, 69, 0, 65, 0, 67, 0, 116, 0, 117, 0, 114, 0, 116, 0, 118, 0, 117, 0, 118, 0, 120, 0, 119, 0, 120, 0, 122, 0, 121, 0, 122, 0, 124, 0, 123, 0, 124, 0, 126, 0, 125, 0, 126, 0, 128, 0, 127, 0, 128, 0, 130, 0, 129, 0, 130, 0, 132, 0, 131, 0, 132, 0, 134, 0, 133, 0, 134, 0, 136, 0, 135, 0, 136, 0, 138, 0, 137, 0, 138, 0, 140, 0, 139, 0, 140, 0, 142, 0, 141, 0, 142, 0, 144, 0, 143, 0, 144, 0, 146, 0, 145, 0, 146, 0, 148, 0, 147, 0, 148, 0, 150, 0, 149, 0, 150, 0, 152, 0, 151, 0, 152, 0, 154, 0, 153, 0, 154, 0, 156, 0, 155, 0, 156, 0, 158, 0, 157, 0, 158, 0, 160, 0, 159, 0, 162, 0, 163, 0, 160, 0, 162, 0, 164, 0, 163, 0, 164, 0, 166, 0, 165, 0, 166, 0, 168, 0, 167, 0, 168, 0, 170, 0, 169, 0, 170, 0, 172, 0, 171, 0, 172, 0, 174, 0, 173, 0, 119, 0, 135, 0, 151, 0, 174, 0, 176, 0, 175, 0, 176, 0, 114, 0, 177, 0, 128, 0, 160, 0, 144, 0, 117, 0, 115, 0, 114, 0, 118, 0, 119, 0, 117, 0, 120, 0, 121, 0, 119, 0, 122, 0, 123, 0, 121, 0, 124, 0, 125, 0, 123, 0, 126, 0, 127, 0, 125, 0, 128, 0, 129, 0, 127, 0, 130, 0, 131, 0, 129, 0, 132, 0, 133, 0, 131, 0, 134, 0, 135, 0, 133, 0, 136, 0, 137, 0, 135, 0, 138, 0, 139, 0, 137, 0, 140, 0, 141, 0, 139, 0, 142, 0, 143, 0, 141, 0, 144, 0, 145, 0, 143, 0, 146, 0, 147, 0, 145, 0, 148, 0, 149, 0, 147, 0, 150, 0, 151, 0, 149, 0, 152, 0, 153, 0, 151, 0, 154, 0, 155, 0, 153, 0, 156, 0, 157, 0, 155, 0, 158, 0, 159, 0, 157, 0, 160, 0, 161, 0, 159, 0, 163, 0, 161, 0, 160, 0, 164, 0, 165, 0, 163, 0, 166, 0, 167, 0, 165, 0, 168, 0, 169, 0, 167, 0, 170, 0, 171, 0, 169, 0, 172, 0, 173, 0, 171, 0, 174, 0, 175, 0, 173, 0, 175, 0, 117, 0, 119, 0, 175, 0, 177, 0, 115, 0, 171, 0, 173, 0, 175, 0, 167, 0, 169, 0, 171, 0, 163, 0, 165, 0, 167, 0, 159, 0, 161, 0, 163, 0, 155, 0, 157, 0, 159, 0, 159, 0, 153, 0, 155, 0, 143, 0, 149, 0, 151, 0, 143, 0, 145, 0, 147, 0, 139, 0, 141, 0, 143, 0, 135, 0, 137, 0, 139, 0, 131, 0, 133, 0, 135, 0, 135, 0, 129, 0, 131, 0, 123, 0, 125, 0, 127, 0, 127, 0, 121, 0, 123, 0, 175, 0, 115, 0, 117, 0, 167, 0, 171, 0, 175, 0, 159, 0, 163, 0, 167, 0, 151, 0, 153, 0, 159, 0, 143, 0, 147, 0, 149, 0, 151, 0, 139, 0, 143, 0, 127, 0, 129, 0, 135, 0, 119, 0, 121, 0, 127, 0, 151, 0, 175, 0, 119, 0, 151, 0, 159, 0, 167, 0, 135, 0, 139, 0, 151, 0, 119, 0, 127, 0, 135, 0, 151, 0, 167, 0, 175, 0, 176, 0, 177, 0, 175, 0, 114, 0, 115, 0, 177, 0, 116, 0, 114, 0, 176, 0, 120, 0, 118, 0, 116, 0, 128, 0, 122, 0, 120, 0, 128, 0, 126, 0, 124, 0, 132, 0, 130, 0, 128, 0, 136, 0, 134, 0, 132, 0, 144, 0, 138, 0, 136, 0, 144, 0, 142, 0, 140, 0, 148, 0, 146, 0, 144, 0, 152, 0, 150, 0, 148, 0, 156, 0, 154, 0, 152, 0, 152, 0, 158, 0, 156, 0, 168, 0, 162, 0, 160, 0, 168, 0, 166, 0, 164, 0, 176, 0, 170, 0, 168, 0, 176, 0, 174, 0, 172, 0, 128, 0, 116, 0, 176, 0, 128, 0, 124, 0, 122, 0, 144, 0, 132, 0, 128, 0, 144, 0, 140, 0, 138, 0, 160, 0, 148, 0, 144, 0, 160, 0, 158, 0, 152, 0, 168, 0, 164, 0, 162, 0, 176, 0, 172, 0, 170, 0, 128, 0, 120, 0, 116, 0, 144, 0, 136, 0, 132, 0, 160, 0, 152, 0, 148, 0, 176, 0, 168, 0, 160, 0, 160, 0, 128, 0, 176, 0};
            mesh.getFaces().addAll(faces);
        }

        setMesh(mesh);
    }
}
