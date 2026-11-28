#version 120

uniform sampler2D texture;
uniform vec2 texelSize;
uniform vec2 direction;
uniform float radius;

varying vec2 uv;

void main() {
    // Box Blur für Shadow (nutzt Original-Farbe wie Gaussian)
    vec3 color = vec3(0.0);
    float totalAlpha = 0.0;
    float totalWeight = 0.0;

    // Box filter - alpha-gewichtete Farb-Akkumulation
    for (float i = -radius; i <= radius; i += 1.0) {
        vec4 smpl = texture2D(texture, uv + direction * i * texelSize);
        float weight = smpl.a;  // Gewicht = Alpha
        color += smpl.rgb * weight;  // Farbe mit Alpha gewichtet
        totalAlpha += smpl.a;
        totalWeight += weight;
    }

    // Durchschnitt berechnen
    float count = radius * 2.0 + 1.0;
    totalAlpha /= count;

    // Farbe durch Gewichtungs-Summe teilen (nur wo Alpha war)
    if (totalWeight > 0.01) {
        color /= totalWeight;
    }

    gl_FragColor = vec4(color, totalAlpha);
}



